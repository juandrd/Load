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
@Table(name = "preexistencia_bodega")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PreexistenciaBodega.findAll", query = "SELECT p FROM PreexistenciaBodega p"),
    @NamedQuery(name = "PreexistenciaBodega.findByPreexistenciaKey", query = "SELECT p FROM PreexistenciaBodega p WHERE p.preexistenciaKey = :preexistenciaKey"),
    @NamedQuery(name = "PreexistenciaBodega.findByIdPaciente", query = "SELECT p FROM PreexistenciaBodega p WHERE p.idPaciente = :idPaciente"),
    @NamedQuery(name = "PreexistenciaBodega.findByFamiliar", query = "SELECT p FROM PreexistenciaBodega p WHERE p.familiar = :familiar"),
    @NamedQuery(name = "PreexistenciaBodega.findByEnfermedad", query = "SELECT p FROM PreexistenciaBodega p WHERE p.enfermedad = :enfermedad")})
public class PreexistenciaBodega implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "preexistencia_key")
    private Integer preexistenciaKey;
    @Column(name = "id_paciente")
    private String idPaciente;
    @Column(name = "familiar")
    private String familiar;
    @Column(name = "enfermedad")
    private String enfermedad;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "preexistenciaBodega")
    private List<CitasBodega> citasBodegaList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "preexistenciaBodega")
    private List<PagosBodega> pagosBodegaList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "preexistenciaBodega")
    private List<UrgenciasBodega> urgenciasBodegaList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "preexistenciaBodega")
    private List<RemisionesBodega> remisionesBodegaList;

    public PreexistenciaBodega() {
    }

    public PreexistenciaBodega(Integer preexistenciaKey) {
        this.preexistenciaKey = preexistenciaKey;
    }

    public Integer getPreexistenciaKey() {
        return preexistenciaKey;
    }

    public void setPreexistenciaKey(Integer preexistenciaKey) {
        this.preexistenciaKey = preexistenciaKey;
    }

    public String getIdPaciente() {
        return idPaciente;
    }

    public void setIdPaciente(String idPaciente) {
        this.idPaciente = idPaciente;
    }

    public String getFamiliar() {
        return familiar;
    }

    public void setFamiliar(String familiar) {
        this.familiar = familiar;
    }

    public String getEnfermedad() {
        return enfermedad;
    }

    public void setEnfermedad(String enfermedad) {
        this.enfermedad = enfermedad;
    }

    @XmlTransient
    public List<CitasBodega> getCitasBodegaList() {
        return citasBodegaList;
    }

    public void setCitasBodegaList(List<CitasBodega> citasBodegaList) {
        this.citasBodegaList = citasBodegaList;
    }

    @XmlTransient
    public List<PagosBodega> getPagosBodegaList() {
        return pagosBodegaList;
    }

    public void setPagosBodegaList(List<PagosBodega> pagosBodegaList) {
        this.pagosBodegaList = pagosBodegaList;
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
        hash += (preexistenciaKey != null ? preexistenciaKey.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PreexistenciaBodega)) {
            return false;
        }
        PreexistenciaBodega other = (PreexistenciaBodega) object;
        if ((this.preexistenciaKey == null && other.preexistenciaKey != null) || (this.preexistenciaKey != null && !this.preexistenciaKey.equals(other.preexistenciaKey))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidades_Bodega.PreexistenciaBodega[ preexistenciaKey=" + preexistenciaKey + " ]";
    }
    
}
