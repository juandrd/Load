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
@Table(name = "diagnostico_bodega")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DiagnosticoBodega.findAll", query = "SELECT d FROM DiagnosticoBodega d"),
    @NamedQuery(name = "DiagnosticoBodega.findByDiagnosticoKey", query = "SELECT d FROM DiagnosticoBodega d WHERE d.diagnosticoKey = :diagnosticoKey"),
    @NamedQuery(name = "DiagnosticoBodega.findByDescripcion", query = "SELECT d FROM DiagnosticoBodega d WHERE d.descripcion = :descripcion")})
public class DiagnosticoBodega implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "diagnostico_key")
    private Integer diagnosticoKey;
    @Column(name = "descripcion")
    private String descripcion;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "diagnosticoBodega")
    private List<CitasBodega> citasBodegaList;
    @OneToMany(mappedBy = "diagnosticoKey")
    private List<UrgenciasBodega> urgenciasBodegaList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "diagnosticoBodega")
    private List<RemisionesBodega> remisionesBodegaList;

    public DiagnosticoBodega() {
    }

    public DiagnosticoBodega(Integer diagnosticoKey) {
        this.diagnosticoKey = diagnosticoKey;
    }

    public Integer getDiagnosticoKey() {
        return diagnosticoKey;
    }

    public void setDiagnosticoKey(Integer diagnosticoKey) {
        this.diagnosticoKey = diagnosticoKey;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
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
        hash += (diagnosticoKey != null ? diagnosticoKey.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DiagnosticoBodega)) {
            return false;
        }
        DiagnosticoBodega other = (DiagnosticoBodega) object;
        if ((this.diagnosticoKey == null && other.diagnosticoKey != null) || (this.diagnosticoKey != null && !this.diagnosticoKey.equals(other.diagnosticoKey))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidades_Bodega.DiagnosticoBodega[ diagnosticoKey=" + diagnosticoKey + " ]";
    }
    
}
