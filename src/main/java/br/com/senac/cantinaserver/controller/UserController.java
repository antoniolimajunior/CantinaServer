package br.com.senac.cantinaserver.controller;

import br.com.senac.cantinaserver.dao.ProductDAO;
import br.com.senac.cantinaserver.model.User;
import br.com.senac.cantinaserver.dao.UserDAO;
import br.com.senac.cantinaserver.exception.BaseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.ws.rs.*;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@RequestMapping("/api/v1/user")
public class UserController {

    @Autowired
    private UserDAO userDao;

    @Autowired
    private ProductDAO productDAO;

    @ResponseBody
    @RequestMapping(method = RequestMethod.POST, value = "login")
    public User login(@FormParam("matricula") String matricula, @FormParam("senha") String senha) {
        User user = userDao.login(matricula, senha);
        if (null == user) {
            throw new BaseException("Usuário e Senha inválidos!");
        }
        return user;
    }

    @ResponseBody
    @RequestMapping(method = RequestMethod.POST, value = "get/{id}")
    public User login(@PathVariable("id") Long id) {
        User user = userDao.findById(id);
        if (null == user) {
            throw new BaseException("Id inválido!");
        }
        return user;
    }

    @ResponseBody
    @RequestMapping(method = RequestMethod.GET, value = "initBD")
    public void initDB() {
        try {
            userDao.initDB();
            productDAO.initDB();
        } catch (Exception ex) {
            throw new BaseException("Problemas ao realizar a geração do BD!");
        }
    }
}
