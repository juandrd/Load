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
@Table(name = "empresa")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Empresa.findAll", query = "SELECT e FROM Empresa e"),
    @NamedQuery(name = "Empresa.findByNit", query = "SELECT e FROM Empresa e WHERE e.nit = :nit"),
    @NamedQuery(name = "Empresa.findByNombre", query = "SELECT e FROM Empresa e WHERE e.nombre = :nombre"),
    @NamedQuery(name = "Empresa.findByTotalEmpleados", query = "SELECT e FROM Empresa e WHERE e.totalEmpleados = :totalEmpleados"),
    @NamedQuery(name = "Empresa.findByDireccion", query = "SELECT e FROM Empresa e WHERE e.direccion = :direccion"),
    @NamedQuery(name = "Empresa.findByActividadEconomica", query = "SELECT e FROM Empresa e WHERE e.actividadEconomica = :actividadEconomica"),
    @NamedQuery(name = "Empresa.findByCiudad", query = "SELECT e FROM Empresa e WHERE e.ciudad = :ciudad")})
public class Empresa implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "NIT")
    private String nit;
    @Column(name = "Nombre")
    private String nombre;
    @Column(name = "Total_Empleados")
    private Integer totalEmpleados;
    @Column(name = "Direccion")
    private String direccion;
    @Column(name = "Actividad_Economica")
    private String actividadEconomica;
    @Column(name = "Ciudad")
    private String ciudad;
    @JoinTable(name = "empresa_cotizante", joinColumns = {
        @JoinColumn(name = "Empresa", referencedColumnName = "NIT")}, inverseJoinColumns = {
        @JoinColumn(name = "Cotizante", referencedColumnName = "Cedula")})
    @ManyToMany
    private List<Cotizante> cotizanteList;

    public Empresa() {
    }

    public Empresa(String nit) {
        this.nit = nit;
    }

    public String getNit() {
        return nit;
    }

    public void setNit(String nit) {
        this.nit = nit;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getTotalEmpleados() {
        return totalEmpleados;
    }

    public void setTotalEmpleados(Integer totalEmpleados) {
        this.totalEmpleados = totalEmpleados;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getActividadEconomica() {
        return actividadEconomica;
    }

    public void setActividadEconomica(String actividadEconomica) {
        this.actividadEconomica = actividadEconomica;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    @XmlTransient
    public List<Cotizante> getCotizanteList() {
        return cotizanteList;
    }

    public void setCotizanteList(List<Cotizante> cotizanteList) {
        this.cotizanteList = cotizanteList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (nit != null ? nit.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Empresa)) {
            return false;
        }
        Empresa other = (Empresa) object;
        if ((this.nit == null && other.nit != null) || (this.nit != null && !this.nit.equals(other.nit))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidades_DB.Empresa[ nit=" + nit + " ]";
    }
    
}
