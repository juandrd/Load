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
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author USER
 */
@Entity
@Table(name = "drogueria")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Drogueria.findAll", query = "SELECT d FROM Drogueria d"),
    @NamedQuery(name = "Drogueria.findByIDDrogueria", query = "SELECT d FROM Drogueria d WHERE d.iDDrogueria = :iDDrogueria"),
    @NamedQuery(name = "Drogueria.findByNombre", query = "SELECT d FROM Drogueria d WHERE d.nombre = :nombre"),
    @NamedQuery(name = "Drogueria.findByDireccion", query = "SELECT d FROM Drogueria d WHERE d.direccion = :direccion"),
    @NamedQuery(name = "Drogueria.findByTelefono", query = "SELECT d FROM Drogueria d WHERE d.telefono = :telefono"),
    @NamedQuery(name = "Drogueria.findByCiudad", query = "SELECT d FROM Drogueria d WHERE d.ciudad = :ciudad")})
public class Drogueria implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID_Drogueria")
    private String iDDrogueria;
    @Column(name = "Nombre")
    private String nombre;
    @Column(name = "Direccion")
    private String direccion;
    @Column(name = "Telefono")
    private String telefono;
    @Column(name = "Ciudad")
    private String ciudad;
    @JoinTable(name = "ips_drogueria", joinColumns = {
        @JoinColumn(name = "ID_Drogueria", referencedColumnName = "ID_Drogueria")}, inverseJoinColumns = {
        @JoinColumn(name = "ID_IPS", referencedColumnName = "ID_IPS")})
    @ManyToMany
    private List<Ips> ipsList;

    public Drogueria() {
    }

    public Drogueria(String iDDrogueria) {
        this.iDDrogueria = iDDrogueria;
    }

    public String getIDDrogueria() {
        return iDDrogueria;
    }

    public void setIDDrogueria(String iDDrogueria) {
        this.iDDrogueria = iDDrogueria;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    @XmlTransient
    public List<Ips> getIpsList() {
        return ipsList;
    }

    public void setIpsList(List<Ips> ipsList) {
        this.ipsList = ipsList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (iDDrogueria != null ? iDDrogueria.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Drogueria)) {
            return false;
        }
        Drogueria other = (Drogueria) object;
        if ((this.iDDrogueria == null && other.iDDrogueria != null) || (this.iDDrogueria != null && !this.iDDrogueria.equals(other.iDDrogueria))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidades_DB.Drogueria[ iDDrogueria=" + iDDrogueria + " ]";
    }
    
}
