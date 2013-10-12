/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades_DB;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author USER
 */
@Entity
@Table(name = "pagos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Pagos.findAll", query = "SELECT p FROM Pagos p"),
    @NamedQuery(name = "Pagos.findByIDTransaccion", query = "SELECT p FROM Pagos p WHERE p.iDTransaccion = :iDTransaccion"),
    @NamedQuery(name = "Pagos.findByFechaPago", query = "SELECT p FROM Pagos p WHERE p.fechaPago = :fechaPago"),
    @NamedQuery(name = "Pagos.findByValorPagado", query = "SELECT p FROM Pagos p WHERE p.valorPagado = :valorPagado")})
public class Pagos implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID_Transaccion")
    private String iDTransaccion;
    @Column(name = "Fecha_Pago")
    @Temporal(TemporalType.DATE)
    private Date fechaPago;
    @Column(name = "Valor_Pagado")
    private Integer valorPagado;
    @JoinColumn(name = "ID_Usuario", referencedColumnName = "Cedula")
    @ManyToOne
    private Cotizante iDUsuario;

    public Pagos() {
    }

    public Pagos(String iDTransaccion) {
        this.iDTransaccion = iDTransaccion;
    }

    public String getIDTransaccion() {
        return iDTransaccion;
    }

    public void setIDTransaccion(String iDTransaccion) {
        this.iDTransaccion = iDTransaccion;
    }

    public Date getFechaPago() {
        return fechaPago;
    }

    public void setFechaPago(Date fechaPago) {
        this.fechaPago = fechaPago;
    }

    public Integer getValorPagado() {
        return valorPagado;
    }

    public void setValorPagado(Integer valorPagado) {
        this.valorPagado = valorPagado;
    }

    public Cotizante getIDUsuario() {
        return iDUsuario;
    }

    public void setIDUsuario(Cotizante iDUsuario) {
        this.iDUsuario = iDUsuario;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (iDTransaccion != null ? iDTransaccion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Pagos)) {
            return false;
        }
        Pagos other = (Pagos) object;
        if ((this.iDTransaccion == null && other.iDTransaccion != null) || (this.iDTransaccion != null && !this.iDTransaccion.equals(other.iDTransaccion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidades_DB.Pagos[ iDTransaccion=" + iDTransaccion + " ]";
    }
    
}
