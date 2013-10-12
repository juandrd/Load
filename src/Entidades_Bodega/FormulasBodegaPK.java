/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades_Bodega;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author USER
 */
@Embeddable
public class FormulasBodegaPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "paciente_key")
    private int pacienteKey;
    @Basic(optional = false)
    @Column(name = "demografia_paciente_key")
    private int demografiaPacienteKey;
    @Basic(optional = false)
    @Column(name = "fecha_key")
    private long fechaKey;
    @Basic(optional = false)
    @Column(name = "medico_key")
    private int medicoKey;
    @Basic(optional = false)
    @Column(name = "medicamento_key")
    private int medicamentoKey;

    public FormulasBodegaPK() {
    }

    public FormulasBodegaPK(int pacienteKey, int demografiaPacienteKey, long fechaKey, int medicoKey, int medicamentoKey) {
        this.pacienteKey = pacienteKey;
        this.demografiaPacienteKey = demografiaPacienteKey;
        this.fechaKey = fechaKey;
        this.medicoKey = medicoKey;
        this.medicamentoKey = medicamentoKey;
    }

    public int getPacienteKey() {
        return pacienteKey;
    }

    public void setPacienteKey(int pacienteKey) {
        this.pacienteKey = pacienteKey;
    }

    public int getDemografiaPacienteKey() {
        return demografiaPacienteKey;
    }

    public void setDemografiaPacienteKey(int demografiaPacienteKey) {
        this.demografiaPacienteKey = demografiaPacienteKey;
    }

    public long getFechaKey() {
        return fechaKey;
    }

    public void setFechaKey(long fechaKey) {
        this.fechaKey = fechaKey;
    }

    public int getMedicoKey() {
        return medicoKey;
    }

    public void setMedicoKey(int medicoKey) {
        this.medicoKey = medicoKey;
    }

    public int getMedicamentoKey() {
        return medicamentoKey;
    }

    public void setMedicamentoKey(int medicamentoKey) {
        this.medicamentoKey = medicamentoKey;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) pacienteKey;
        hash += (int) demografiaPacienteKey;
        hash += (int) fechaKey;
        hash += (int) medicoKey;
        hash += (int) medicamentoKey;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FormulasBodegaPK)) {
            return false;
        }
        FormulasBodegaPK other = (FormulasBodegaPK) object;
        if (this.pacienteKey != other.pacienteKey) {
            return false;
        }
        if (this.demografiaPacienteKey != other.demografiaPacienteKey) {
            return false;
        }
        if (this.fechaKey != other.fechaKey) {
            return false;
        }
        if (this.medicoKey != other.medicoKey) {
            return false;
        }
        if (this.medicamentoKey != other.medicamentoKey) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidades_Bodega.FormulasBodegaPK[ pacienteKey=" + pacienteKey + ", demografiaPacienteKey=" + demografiaPacienteKey + ", fechaKey=" + fechaKey + ", medicoKey=" + medicoKey + ", medicamentoKey=" + medicamentoKey + " ]";
    }
    
}
