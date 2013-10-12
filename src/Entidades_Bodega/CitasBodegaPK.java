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
public class CitasBodegaPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "medico_key")
    private int medicoKey;
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
    @Column(name = "diagnostico_key")
    private int diagnosticoKey;
    @Basic(optional = false)
    @Column(name = "preexistencia_key")
    private int preexistenciaKey;
    @Basic(optional = false)
    @Column(name = "servicio_pos_key")
    private int servicioPosKey;
    @Basic(optional = false)
    @Column(name = "ips_key")
    private int ipsKey;

    public CitasBodegaPK() {
    }

    public CitasBodegaPK(int medicoKey, int pacienteKey, int demografiaPacienteKey, long fechaKey, int diagnosticoKey, int preexistenciaKey, int servicioPosKey, int ipsKey) {
        this.medicoKey = medicoKey;
        this.pacienteKey = pacienteKey;
        this.demografiaPacienteKey = demografiaPacienteKey;
        this.fechaKey = fechaKey;
        this.diagnosticoKey = diagnosticoKey;
        this.preexistenciaKey = preexistenciaKey;
        this.servicioPosKey = servicioPosKey;
        this.ipsKey = ipsKey;
    }

    public int getMedicoKey() {
        return medicoKey;
    }

    public void setMedicoKey(int medicoKey) {
        this.medicoKey = medicoKey;
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

    public int getDiagnosticoKey() {
        return diagnosticoKey;
    }

    public void setDiagnosticoKey(int diagnosticoKey) {
        this.diagnosticoKey = diagnosticoKey;
    }

    public int getPreexistenciaKey() {
        return preexistenciaKey;
    }

    public void setPreexistenciaKey(int preexistenciaKey) {
        this.preexistenciaKey = preexistenciaKey;
    }

    public int getServicioPosKey() {
        return servicioPosKey;
    }

    public void setServicioPosKey(int servicioPosKey) {
        this.servicioPosKey = servicioPosKey;
    }

    public int getIpsKey() {
        return ipsKey;
    }

    public void setIpsKey(int ipsKey) {
        this.ipsKey = ipsKey;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) medicoKey;
        hash += (int) pacienteKey;
        hash += (int) demografiaPacienteKey;
        hash += (int) fechaKey;
        hash += (int) diagnosticoKey;
        hash += (int) preexistenciaKey;
        hash += (int) servicioPosKey;
        hash += (int) ipsKey;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CitasBodegaPK)) {
            return false;
        }
        CitasBodegaPK other = (CitasBodegaPK) object;
        if (this.medicoKey != other.medicoKey) {
            return false;
        }
        if (this.pacienteKey != other.pacienteKey) {
            return false;
        }
        if (this.demografiaPacienteKey != other.demografiaPacienteKey) {
            return false;
        }
        if (this.fechaKey != other.fechaKey) {
            return false;
        }
        if (this.diagnosticoKey != other.diagnosticoKey) {
            return false;
        }
        if (this.preexistenciaKey != other.preexistenciaKey) {
            return false;
        }
        if (this.servicioPosKey != other.servicioPosKey) {
            return false;
        }
        if (this.ipsKey != other.ipsKey) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidades_Bodega.CitasBodegaPK[ medicoKey=" + medicoKey + ", pacienteKey=" + pacienteKey + ", demografiaPacienteKey=" + demografiaPacienteKey + ", fechaKey=" + fechaKey + ", diagnosticoKey=" + diagnosticoKey + ", preexistenciaKey=" + preexistenciaKey + ", servicioPosKey=" + servicioPosKey + ", ipsKey=" + ipsKey + " ]";
    }
    
}
