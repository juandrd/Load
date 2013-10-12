/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades_DB;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author USER
 */
@Entity
@Table(name = "formulas_medicas")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "FormulasMedicas.findAll", query = "SELECT f FROM FormulasMedicas f"),
    @NamedQuery(name = "FormulasMedicas.findByCodigoFormula", query = "SELECT f FROM FormulasMedicas f WHERE f.codigoFormula = :codigoFormula"),
    @NamedQuery(name = "FormulasMedicas.findByIDUsuario", query = "SELECT f FROM FormulasMedicas f WHERE f.iDUsuario = :iDUsuario"),
    @NamedQuery(name = "FormulasMedicas.findByFecha", query = "SELECT f FROM FormulasMedicas f WHERE f.fecha = :fecha"),
    @NamedQuery(name = "FormulasMedicas.findByMedicamentosRecetados", query = "SELECT f FROM FormulasMedicas f WHERE f.medicamentosRecetados = :medicamentosRecetados")})
public class FormulasMedicas implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "Codigo_Formula")
    private String codigoFormula;
    @Column(name = "ID_Usuario")
    private String iDUsuario;
    @Column(name = "Fecha")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @Column(name = "Medicamentos_Recetados")
    private String medicamentosRecetados;
    @JoinColumn(name = "ID_Medico", referencedColumnName = "Cedula")
    @ManyToOne
    private Medico iDMedico;

    public FormulasMedicas() {
    }

    public FormulasMedicas(String codigoFormula) {
        this.codigoFormula = codigoFormula;
    }

    public String getCodigoFormula() {
        return codigoFormula;
    }

    public void setCodigoFormula(String codigoFormula) {
        this.codigoFormula = codigoFormula;
    }

    public String getIDUsuario() {
        return iDUsuario;
    }

    public void setIDUsuario(String iDUsuario) {
        this.iDUsuario = iDUsuario;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getMedicamentosRecetados() {
        return medicamentosRecetados;
    }

    public void setMedicamentosRecetados(String medicamentosRecetados) {
        this.medicamentosRecetados = medicamentosRecetados;
    }

    public Medico getIDMedico() {
        return iDMedico;
    }

    public void setIDMedico(Medico iDMedico) {
        this.iDMedico = iDMedico;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codigoFormula != null ? codigoFormula.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FormulasMedicas)) {
            return false;
        }
        FormulasMedicas other = (FormulasMedicas) object;
        if ((this.codigoFormula == null && other.codigoFormula != null) || (this.codigoFormula != null && !this.codigoFormula.equals(other.codigoFormula))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidades_DB.FormulasMedicas[ codigoFormula=" + codigoFormula + " ]";
    }
    
}
