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
@Table(name = "formulas_bodega")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "FormulasBodega.findAll", query = "SELECT f FROM FormulasBodega f"),
    @NamedQuery(name = "FormulasBodega.findByPacienteKey", query = "SELECT f FROM FormulasBodega f WHERE f.formulasBodegaPK.pacienteKey = :pacienteKey"),
    @NamedQuery(name = "FormulasBodega.findByDemografiaPacienteKey", query = "SELECT f FROM FormulasBodega f WHERE f.formulasBodegaPK.demografiaPacienteKey = :demografiaPacienteKey"),
    @NamedQuery(name = "FormulasBodega.findByFechaKey", query = "SELECT f FROM FormulasBodega f WHERE f.formulasBodegaPK.fechaKey = :fechaKey"),
    @NamedQuery(name = "FormulasBodega.findByMedicoKey", query = "SELECT f FROM FormulasBodega f WHERE f.formulasBodegaPK.medicoKey = :medicoKey"),
    @NamedQuery(name = "FormulasBodega.findByMedicamentoKey", query = "SELECT f FROM FormulasBodega f WHERE f.formulasBodegaPK.medicamentoKey = :medicamentoKey"),
    @NamedQuery(name = "FormulasBodega.findByCostoReceta", query = "SELECT f FROM FormulasBodega f WHERE f.costoReceta = :costoReceta"),
    @NamedQuery(name = "FormulasBodega.findByNroMedicamentos", query = "SELECT f FROM FormulasBodega f WHERE f.nroMedicamentos = :nroMedicamentos")})
public class FormulasBodega implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected FormulasBodegaPK formulasBodegaPK;
    @Column(name = "costo_receta")
    private Integer costoReceta;
    @Column(name = "nro_medicamentos")
    private Integer nroMedicamentos;
    @JoinColumn(name = "medicamento_key", referencedColumnName = "medicamento_key", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private MedicamentoBodega medicamentoBodega;
    @JoinColumn(name = "medico_key", referencedColumnName = "medico_key", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private MedicoBodega medicoBodega;
    @JoinColumn(name = "fecha_key", referencedColumnName = "date_id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Dates dates;
    @JoinColumn(name = "demografia_paciente_key", referencedColumnName = "demografia_paciente_key", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private DemografiaPacienteBodega demografiaPacienteBodega;
    @JoinColumn(name = "paciente_key", referencedColumnName = "paciente_key", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private PacienteBodega pacienteBodega;

    public FormulasBodega() {
    }

    public FormulasBodega(FormulasBodegaPK formulasBodegaPK) {
        this.formulasBodegaPK = formulasBodegaPK;
    }

    public FormulasBodega(int pacienteKey, int demografiaPacienteKey, long fechaKey, int medicoKey, int medicamentoKey) {
        this.formulasBodegaPK = new FormulasBodegaPK(pacienteKey, demografiaPacienteKey, fechaKey, medicoKey, medicamentoKey);
    }

    public FormulasBodegaPK getFormulasBodegaPK() {
        return formulasBodegaPK;
    }

    public void setFormulasBodegaPK(FormulasBodegaPK formulasBodegaPK) {
        this.formulasBodegaPK = formulasBodegaPK;
    }

    public Integer getCostoReceta() {
        return costoReceta;
    }

    public void setCostoReceta(Integer costoReceta) {
        this.costoReceta = costoReceta;
    }

    public Integer getNroMedicamentos() {
        return nroMedicamentos;
    }

    public void setNroMedicamentos(Integer nroMedicamentos) {
        this.nroMedicamentos = nroMedicamentos;
    }

    public MedicamentoBodega getMedicamentoBodega() {
        return medicamentoBodega;
    }

    public void setMedicamentoBodega(MedicamentoBodega medicamentoBodega) {
        this.medicamentoBodega = medicamentoBodega;
    }

    public MedicoBodega getMedicoBodega() {
        return medicoBodega;
    }

    public void setMedicoBodega(MedicoBodega medicoBodega) {
        this.medicoBodega = medicoBodega;
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
        hash += (formulasBodegaPK != null ? formulasBodegaPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FormulasBodega)) {
            return false;
        }
        FormulasBodega other = (FormulasBodega) object;
        if ((this.formulasBodegaPK == null && other.formulasBodegaPK != null) || (this.formulasBodegaPK != null && !this.formulasBodegaPK.equals(other.formulasBodegaPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidades_Bodega.FormulasBodega[ formulasBodegaPK=" + formulasBodegaPK + " ]";
    }
    
}
