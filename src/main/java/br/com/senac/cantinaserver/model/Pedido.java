package br.com.senac.cantinaserver.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PostPersist;

@Entity
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @OneToOne
    private User user;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pedido", orphanRemoval = true)
    private List<PedidoItem> pedidosItem = new ArrayList<>();

    private BigDecimal valorTotal;
    
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<PedidoItem> getPedidosItem() {
        return pedidosItem;
    }

    public void setPedidosItem(List<PedidoItem> pedidosItem) {
        this.pedidosItem = pedidosItem;
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(BigDecimal valorTotal) {
        this.valorTotal = valorTotal;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    
    @PostPersist
    public void setPedidoID() {
        for (PedidoItem pei : this.pedidosItem) {
            pei.setPedido(this);
        }
    }
}
