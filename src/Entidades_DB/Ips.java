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
import javax.persistence.ManyToMany;
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
@Table(name = "ips")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Ips.findAll", query = "SELECT i FROM Ips i"),
    @NamedQuery(name = "Ips.findByIdIps", query = "SELECT i FROM Ips i WHERE i.idIps = :idIps"),
    @NamedQuery(name = "Ips.findByTipoIPS", query = "SELECT i FROM Ips i WHERE i.tipoIPS = :tipoIPS"),
    @NamedQuery(name = "Ips.findByNombre", query = "SELECT i FROM Ips i WHERE i.nombre = :nombre"),
    @NamedQuery(name = "Ips.findByDireccion", query = "SELECT i FROM Ips i WHERE i.direccion = :direccion"),
    @NamedQuery(name = "Ips.findByNivel", query = "SELECT i FROM Ips i WHERE i.nivel = :nivel"),
    @NamedQuery(name = "Ips.findByMunicipio", query = "SELECT i FROM Ips i WHERE i.municipio = :municipio"),
    @NamedQuery(name = "Ips.findByDepartamento", query = "SELECT i FROM Ips i WHERE i.departamento = :departamento")})
public class Ips implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID_IPS")
    private String idIps;
    @Column(name = "Tipo_IPS")
    private String tipoIPS;
    @Column(name = "Nombre")
    private String nombre;
    @Column(name = "Direccion")
    private String direccion;
    @Column(name = "Nivel")
    private String nivel;
    @Column(name = "Municipio")
    private String municipio;
    @Column(name = "Departamento")
    private String departamento;
    @ManyToMany(mappedBy = "ipsList")
    private List<Drogueria> drogueriaList;
    @OneToMany(mappedBy = "idIps")
    private List<Cotizante> cotizanteList;

    public Ips() {
    }

    public Ips(String idIps) {
        this.idIps = idIps;
    }

    public String getIdIps() {
        return idIps;
    }

    public void setIdIps(String idIps) {
        this.idIps = idIps;
    }

    public String getTipoIPS() {
        return tipoIPS;
    }

    public void setTipoIPS(String tipoIPS) {
        this.tipoIPS = tipoIPS;
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

    public String getNivel() {
        return nivel;
    }

    public void setNivel(String nivel) {
        this.nivel = nivel;
    }

    public String getMunicipio() {
        return municipio;
    }

    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    @XmlTransient
    public List<Drogueria> getDrogueriaList() {
        return drogueriaList;
    }

    public void setDrogueriaList(List<Drogueria> drogueriaList) {
        this.drogueriaList = drogueriaList;
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
        hash += (idIps != null ? idIps.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Ips)) {
            return false;
        }
        Ips other = (Ips) object;
        if ((this.idIps == null && other.idIps != null) || (this.idIps != null && !this.idIps.equals(other.idIps))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidades_DB.Ips[ idIps=" + idIps + " ]";
    }
    
}
