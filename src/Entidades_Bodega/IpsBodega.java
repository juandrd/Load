/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades_Bodega;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "ips_bodega")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "IpsBodega.findAll", query = "SELECT i FROM IpsBodega i"),
    @NamedQuery(name = "IpsBodega.findByIpsKey", query = "SELECT i FROM IpsBodega i WHERE i.ipsKey = :ipsKey"),
    @NamedQuery(name = "IpsBodega.findByCodigoIps", query = "SELECT i FROM IpsBodega i WHERE i.codigoIps = :codigoIps"),
    @NamedQuery(name = "IpsBodega.findByTipo", query = "SELECT i FROM IpsBodega i WHERE i.tipo = :tipo"),
    @NamedQuery(name = "IpsBodega.findByNombre", query = "SELECT i FROM IpsBodega i WHERE i.nombre = :nombre"),
    @NamedQuery(name = "IpsBodega.findByDireccion", query = "SELECT i FROM IpsBodega i WHERE i.direccion = :direccion"),
    @NamedQuery(name = "IpsBodega.findByMunicipio", query = "SELECT i FROM IpsBodega i WHERE i.municipio = :municipio")})
public class IpsBodega implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ips_key")
    private Integer ipsKey;
    @Column(name = "codigo_ips")
    private String codigoIps;
    @Column(name = "tipo")
    private String tipo;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "direccion")
    private String direccion;
    @Column(name = "municipio")
    private String municipio;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "ipsBodega")
    private List<CitasBodega> citasBodegaList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "ipsBodega")
    private List<AfiliacionesBodega> afiliacionesBodegaList;
    @JoinColumn(name = "departamento", referencedColumnName = "cod_dpto")
    @ManyToOne
    private DepartamentoBodega departamento;
    @OneToMany(mappedBy = "ipsKey")
    private List<UrgenciasBodega> urgenciasBodegaList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "ipsBodega")
    private List<RemisionesBodega> remisionesBodegaList;

    public IpsBodega() {
    }

    public IpsBodega(Integer ipsKey) {
        this.ipsKey = ipsKey;
    }

    public Integer getIpsKey() {
        return ipsKey;
    }

    public void setIpsKey(Integer ipsKey) {
        this.ipsKey = ipsKey;
    }

    public String getCodigoIps() {
        return codigoIps;
    }

    public void setCodigoIps(String codigoIps) {
        this.codigoIps = codigoIps;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
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

    public String getMunicipio() {
        return municipio;
    }

    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }

    @XmlTransient
    public List<CitasBodega> getCitasBodegaList() {
        return citasBodegaList;
    }

    public void setCitasBodegaList(List<CitasBodega> citasBodegaList) {
        this.citasBodegaList = citasBodegaList;
    }

    @XmlTransient
    public List<AfiliacionesBodega> getAfiliacionesBodegaList() {
        return afiliacionesBodegaList;
    }

    public void setAfiliacionesBodegaList(List<AfiliacionesBodega> afiliacionesBodegaList) {
        this.afiliacionesBodegaList = afiliacionesBodegaList;
    }

    public DepartamentoBodega getDepartamento() {
        return departamento;
    }

    public void setDepartamento(DepartamentoBodega departamento) {
        this.departamento = departamento;
    }

    @XmlTransient
    public List<UrgenciasBodega> getUrgenciasBodegaList() {
        return urgenciasBodegaList;
    }

    public void setUrgenciasBodegaList(List<UrgenciasBodega> urgenciasBodegaList) {
        this.urgenciasBodegaList = urgenciasBodegaList;
    }

    @XmlTransient
    public List<RemisionesBodega> getRemisionesBodegaList() {
        return remisionesBodegaList;
    }

    public void setRemisionesBodegaList(List<RemisionesBodega> remisionesBodegaList) {
        this.remisionesBodegaList = remisionesBodegaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (ipsKey != null ? ipsKey.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof IpsBodega)) {
            return false;
        }
        IpsBodega other = (IpsBodega) object;
        if ((this.ipsKey == null && other.ipsKey != null) || (this.ipsKey != null && !this.ipsKey.equals(other.ipsKey))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidades_Bodega.IpsBodega[ ipsKey=" + ipsKey + " ]";
    }
    
}
