/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades_Bodega;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author USER
 */
@Entity
@Table(name = "retiros_bodega")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RetirosBodega.findAll", query = "SELECT r FROM RetirosBodega r"),
    @NamedQuery(name = "RetirosBodega.findByPacienteKey", query = "SELECT r FROM RetirosBodega r WHERE r.retirosBodegaPK.pacienteKey = :pacienteKey"),
    @NamedQuery(name = "RetirosBodega.findByDemografiaPacienteKey", query = "SELECT r FROM RetirosBodega r WHERE r.retirosBodegaPK.demografiaPacienteKey = :demografiaPacienteKey"),
    @NamedQuery(name = "RetirosBodega.findByFechaKey", query = "SELECT r FROM RetirosBodega r WHERE r.retirosBodegaPK.fechaKey = :fechaKey"),
    @NamedQuery(name = "RetirosBodega.findByNuevaEPS", query = "SELECT r FROM RetirosBodega r WHERE r.nuevaEPS = :nuevaEPS")})
public class RetirosBodega implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected RetirosBodegaPK retirosBodegaPK;
    @Column(name = "nueva_EPS")
    private String nuevaEPS;
    @JoinColumn(name = "fecha_key", referencedColumnName = "date_id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Dates dates;
    @JoinColumn(name = "demografia_paciente_key", referencedColumnName = "demografia_paciente_key", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private DemografiaPacienteBodega demografiaPacienteBodega;
    @JoinColumn(name = "paciente_key", referencedColumnName = "paciente_key", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private PacienteBodega pacienteBodega;

    public RetirosBodega() {
    }

    public RetirosBodega(RetirosBodegaPK retirosBodegaPK) {
        this.retirosBodegaPK = retirosBodegaPK;
    }

    public RetirosBodega(int pacienteKey, int demografiaPacienteKey, long fechaKey) {
        this.retirosBodegaPK = new RetirosBodegaPK(pacienteKey, demografiaPacienteKey, fechaKey);
    }

    public RetirosBodegaPK getRetirosBodegaPK() {
        return retirosBodegaPK;
    }

    public void setRetirosBodegaPK(RetirosBodegaPK retirosBodegaPK) {
        this.retirosBodegaPK = retirosBodegaPK;
    }

    public String getNuevaEPS() {
        return nuevaEPS;
    }

    public void setNuevaEPS(String nuevaEPS) {
        this.nuevaEPS = nuevaEPS;
    }

    public Dates getDates() {
        return dates;
    }

    public void setDates(Dates dates) {
        this.dates = dates;
    }

    public DemografiaPacienteBodega getDemografiaPacienteBodega() {
        return demografiaPacienteBodega;
    }

    public void setDemografiaPacienteBodega(DemografiaPacienteBodega demografiaPacienteBodega) {
        this.demografiaPacienteBodega = demografiaPacienteBodega;
    }

    public PacienteBodega getPacienteBodega() {
        return pacienteBodega;
    }

    public void setPacienteBodega(PacienteBodega pacienteBodega) {
        this.pacienteBodega = pacienteBodega;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (retirosBodegaPK != null ? retirosBodegaPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RetirosBodega)) {
            return false;
        }
        RetirosBodega other = (RetirosBodega) object;
        if ((this.retirosBodegaPK == null && other.retirosBodegaPK != null) || (this.retirosBodegaPK != null && !this.retirosBodegaPK.equals(other.retirosBodegaPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidades_Bodega.RetirosBodega[ retirosBodegaPK=" + retirosBodegaPK + " ]";
    }
    
}
