package br.com.senac.cantinaserver.controller;

import br.com.senac.cantinaserver.dao.PedidoDAO;
import br.com.senac.cantinaserver.dao.UserDAO;
import br.com.senac.cantinaserver.model.Pedido;
import br.com.senac.cantinaserver.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class PedidoController {

    @Autowired
    private PedidoDAO pedidoDAO;
    
    @Autowired
    private UserDAO userDAO;

    @ResponseBody
    @RequestMapping(value = "/api/v1/pedido/create", method = RequestMethod.POST)
    public String create(@RequestBody Pedido pedido) {
        try {
            User user = userDAO.getById(pedido.getUser().getId());
            if (pedido.getValorTotal().compareTo(user.getSaldo()) <=0){
                user.setSaldo(user.getSaldo().subtract(pedido.getValorTotal()));
                userDAO.update(user);
                pedidoDAO.create(pedido);
            } else {
                return "saldo insufuciente";        
            }
        } catch (Exception ex) {
            return "Error creating the user: " + ex.toString();
        }
        return "User succesfully created!";
    }
}
