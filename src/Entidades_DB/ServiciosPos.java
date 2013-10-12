/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades_DB;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author USER
 */
@Entity
@Table(name = "servicios_pos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ServiciosPos.findAll", query = "SELECT s FROM ServiciosPos s"),
    @NamedQuery(name = "ServiciosPos.findByIDServicioPOS", query = "SELECT s FROM ServiciosPos s WHERE s.iDServicioPOS = :iDServicioPOS"),
    @NamedQuery(name = "ServiciosPos.findByDescripcion", query = "SELECT s FROM ServiciosPos s WHERE s.descripcion = :descripcion"),
    @NamedQuery(name = "ServiciosPos.findByCosto", query = "SELECT s FROM ServiciosPos s WHERE s.costo = :costo")})
public class ServiciosPos implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID_Servicio_POS")
    private String iDServicioPOS;
    @Column(name = "Descripcion")
    private String descripcion;
    @Column(name = "Costo")
    private Integer costo;
    @OneToMany(mappedBy = "servicioPOS")
    private List<Remisiones> remisionesList;

    public ServiciosPos() {
    }

    public ServiciosPos(String iDServicioPOS) {
        this.iDServicioPOS = iDServicioPOS;
    }

    public String getIDServicioPOS() {
        return iDServicioPOS;
    }

    public void setIDServicioPOS(String iDServicioPOS) {
        this.iDServicioPOS = iDServicioPOS;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Integer getCosto() {
        return costo;
    }

    public void setCosto(Integer costo) {
        this.costo = costo;
    }

    @XmlTransient
    public List<Remisiones> getRemisionesList() {
        return remisionesList;
    }

    public void setRemisionesList(List<Remisiones> remisionesList) {
        this.remisionesList = remisionesList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (iDServicioPOS != null ? iDServicioPOS.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ServiciosPos)) {
            return false;
        }
        ServiciosPos other = (ServiciosPos) object;
        if ((this.iDServicioPOS == null && other.iDServicioPOS != null) || (this.iDServicioPOS != null && !this.iDServicioPOS.equals(other.iDServicioPOS))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidades_DB.ServiciosPos[ iDServicioPOS=" + iDServicioPOS + " ]";
    }
    
}
