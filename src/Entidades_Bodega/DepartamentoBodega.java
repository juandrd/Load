/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades_Bodega;

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
@Table(name = "departamento_bodega")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DepartamentoBodega.findAll", query = "SELECT d FROM DepartamentoBodega d"),
    @NamedQuery(name = "DepartamentoBodega.findByCodDpto", query = "SELECT d FROM DepartamentoBodega d WHERE d.codDpto = :codDpto"),
    @NamedQuery(name = "DepartamentoBodega.findByNombre", query = "SELECT d FROM DepartamentoBodega d WHERE d.nombre = :nombre"),
    @NamedQuery(name = "DepartamentoBodega.findByRegion", query = "SELECT d FROM DepartamentoBodega d WHERE d.region = :region")})
public class DepartamentoBodega implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "cod_dpto")
    private Integer codDpto;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "region")
    private String region;
    @OneToMany(mappedBy = "departamento")
    private List<IpsBodega> ipsBodegaList;

    public DepartamentoBodega() {
    }

    public DepartamentoBodega(Integer codDpto) {
        this.codDpto = codDpto;
    }

    public Integer getCodDpto() {
        return codDpto;
    }

    public void setCodDpto(Integer codDpto) {
        this.codDpto = codDpto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    @XmlTransient
    public List<IpsBodega> getIpsBodegaList() {
        return ipsBodegaList;
    }

    public void setIpsBodegaList(List<IpsBodega> ipsBodegaList) {
        this.ipsBodegaList = ipsBodegaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codDpto != null ? codDpto.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DepartamentoBodega)) {
            return false;
        }
        DepartamentoBodega other = (DepartamentoBodega) object;
        if ((this.codDpto == null && other.codDpto != null) || (this.codDpto != null && !this.codDpto.equals(other.codDpto))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidades_Bodega.DepartamentoBodega[ codDpto=" + codDpto + " ]";
    }
    
}
