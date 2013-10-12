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
@Table(name = "pagos_bodega")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PagosBodega.findAll", query = "SELECT p FROM PagosBodega p"),
    @NamedQuery(name = "PagosBodega.findByPacienteKey", query = "SELECT p FROM PagosBodega p WHERE p.pagosBodegaPK.pacienteKey = :pacienteKey"),
    @NamedQuery(name = "PagosBodega.findByDemografiaPacienteKey", query = "SELECT p FROM PagosBodega p WHERE p.pagosBodegaPK.demografiaPacienteKey = :demografiaPacienteKey"),
    @NamedQuery(name = "PagosBodega.findByFechaKey", query = "SELECT p FROM PagosBodega p WHERE p.pagosBodegaPK.fechaKey = :fechaKey"),
    @NamedQuery(name = "PagosBodega.findByEmpresaKey", query = "SELECT p FROM PagosBodega p WHERE p.pagosBodegaPK.empresaKey = :empresaKey"),
    @NamedQuery(name = "PagosBodega.findByPreexistenciaKey", query = "SELECT p FROM PagosBodega p WHERE p.pagosBodegaPK.preexistenciaKey = :preexistenciaKey"),
    @NamedQuery(name = "PagosBodega.findByValorPagado", query = "SELECT p FROM PagosBodega p WHERE p.valorPagado = :valorPagado")})
public class PagosBodega implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected PagosBodegaPK pagosBodegaPK;
    @Column(name = "valor_pagado")
    private Integer valorPagado;
    @JoinColumn(name = "preexistencia_key", referencedColumnName = "preexistencia_key", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private PreexistenciaBodega preexistenciaBodega;
    @JoinColumn(name = "empresa_key", referencedColumnName = "empresa_key", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private EmpresaBodega empresaBodega;
    @JoinColumn(name = "fecha_key", referencedColumnName = "date_id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Dates dates;
    @JoinColumn(name = "demografia_paciente_key", referencedColumnName = "demografia_paciente_key", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private DemografiaPacienteBodega demografiaPacienteBodega;
    @JoinColumn(name = "paciente_key", referencedColumnName = "paciente_key", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private PacienteBodega pacienteBodega;

    public PagosBodega() {
    }

    public PagosBodega(PagosBodegaPK pagosBodegaPK) {
        this.pagosBodegaPK = pagosBodegaPK;
    }

    public PagosBodega(int pacienteKey, int demografiaPacienteKey, long fechaKey, int empresaKey, int preexistenciaKey) {
        this.pagosBodegaPK = new PagosBodegaPK(pacienteKey, demografiaPacienteKey, fechaKey, empresaKey, preexistenciaKey);
    }

    public PagosBodegaPK getPagosBodegaPK() {
        return pagosBodegaPK;
    }

    public void setPagosBodegaPK(PagosBodegaPK pagosBodegaPK) {
        this.pagosBodegaPK = pagosBodegaPK;
    }

    public Integer getValorPagado() {
        return valorPagado;
    }

    public void setValorPagado(Integer valorPagado) {
        this.valorPagado = valorPagado;
    }

    public PreexistenciaBodega getPreexistenciaBodega() {
        return preexistenciaBodega;
    }

    public void setPreexistenciaBodega(PreexistenciaBodega preexistenciaBodega) {
        this.preexistenciaBodega = preexistenciaBodega;
    }

    public EmpresaBodega getEmpresaBodega() {
        return empresaBodega;
    }

    public void setEmpresaBodega(EmpresaBodega empresaBodega) {
        this.empresaBodega = empresaBodega;
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
        hash += (pagosBodegaPK != null ? pagosBodegaPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PagosBodega)) {
            return false;
        }
        PagosBodega other = (PagosBodega) object;
        if ((this.pagosBodegaPK == null && other.pagosBodegaPK != null) || (this.pagosBodegaPK != null && !this.pagosBodegaPK.equals(other.pagosBodegaPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidades_Bodega.PagosBodega[ pagosBodegaPK=" + pagosBodegaPK + " ]";
    }
    
}
