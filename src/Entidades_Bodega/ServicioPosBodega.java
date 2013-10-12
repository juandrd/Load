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
@Table(name = "servicio_pos_bodega")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ServicioPosBodega.findAll", query = "SELECT s FROM ServicioPosBodega s"),
    @NamedQuery(name = "ServicioPosBodega.findByServicioPosKey", query = "SELECT s FROM ServicioPosBodega s WHERE s.servicioPosKey = :servicioPosKey"),
    @NamedQuery(name = "ServicioPosBodega.findByCodServicio", query = "SELECT s FROM ServicioPosBodega s WHERE s.codServicio = :codServicio"),
    @NamedQuery(name = "ServicioPosBodega.findByDescripcion", query = "SELECT s FROM ServicioPosBodega s WHERE s.descripcion = :descripcion"),
    @NamedQuery(name = "ServicioPosBodega.findByCosto", query = "SELECT s FROM ServicioPosBodega s WHERE s.costo = :costo")})
public class ServicioPosBodega implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "servicio_pos_key")
    private Integer servicioPosKey;
    @Column(name = "cod_servicio")
    private String codServicio;
    @Column(name = "descripcion")
    private String descripcion;
    @Column(name = "costo")
    private Integer costo;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "servicioPosBodega")
    private List<CitasBodega> citasBodegaList;
    @OneToMany(mappedBy = "servicioPosKey")
    private List<UrgenciasBodega> urgenciasBodegaList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "servicioPosBodega")
    private List<RemisionesBodega> remisionesBodegaList;

    public ServicioPosBodega() {
    }

    public ServicioPosBodega(Integer servicioPosKey) {
        this.servicioPosKey = servicioPosKey;
    }

    public Integer getServicioPosKey() {
        return servicioPosKey;
    }

    public void setServicioPosKey(Integer servicioPosKey) {
        this.servicioPosKey = servicioPosKey;
    }

    public String getCodServicio() {
        return codServicio;
    }

    public void setCodServicio(String codServicio) {
        this.codServicio = codServicio;
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
    public List<CitasBodega> getCitasBodegaList() {
        return citasBodegaList;
    }

    public void setCitasBodegaList(List<CitasBodega> citasBodegaList) {
        this.citasBodegaList = citasBodegaList;
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
        hash += (servicioPosKey != null ? servicioPosKey.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ServicioPosBodega)) {
            return false;
        }
        ServicioPosBodega other = (ServicioPosBodega) object;
        if ((this.servicioPosKey == null && other.servicioPosKey != null) || (this.servicioPosKey != null && !this.servicioPosKey.equals(other.servicioPosKey))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidades_Bodega.ServicioPosBodega[ servicioPosKey=" + servicioPosKey + " ]";
    }
    
}
