/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades_DB;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author USER
 */
@Entity
@Table(name = "medicamento")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Medicamento.findAll", query = "SELECT m FROM Medicamento m"),
    @NamedQuery(name = "Medicamento.findByCodigo", query = "SELECT m FROM Medicamento m WHERE m.codigo = :codigo"),
    @NamedQuery(name = "Medicamento.findByNombreGenerico", query = "SELECT m FROM Medicamento m WHERE m.nombreGenerico = :nombreGenerico"),
    @NamedQuery(name = "Medicamento.findByFormaFarmaceutica", query = "SELECT m FROM Medicamento m WHERE m.formaFarmaceutica = :formaFarmaceutica"),
    @NamedQuery(name = "Medicamento.findByPresentacion", query = "SELECT m FROM Medicamento m WHERE m.presentacion = :presentacion"),
    @NamedQuery(name = "Medicamento.findByLaboratorioRegistro", query = "SELECT m FROM Medicamento m WHERE m.laboratorioRegistro = :laboratorioRegistro"),
    @NamedQuery(name = "Medicamento.findByPrecio", query = "SELECT m FROM Medicamento m WHERE m.precio = :precio"),
    @NamedQuery(name = "Medicamento.findByTipoMedicamento", query = "SELECT m FROM Medicamento m WHERE m.tipoMedicamento = :tipoMedicamento")})
public class Medicamento implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "codigo")
    private Integer codigo;
    @Basic(optional = false)
    @Column(name = "nombre_generico")
    private String nombreGenerico;
    @Basic(optional = false)
    @Column(name = "forma_farmaceutica")
    private String formaFarmaceutica;
    @Basic(optional = false)
    @Column(name = "presentacion")
    private String presentacion;
    @Basic(optional = false)
    @Column(name = "laboratorio_registro")
    private String laboratorioRegistro;
    @Basic(optional = false)
    @Column(name = "precio")
    private int precio;
    @Basic(optional = false)
    @Column(name = "tipo_medicamento")
    private String tipoMedicamento;

    public Medicamento() {
    }

    public Medicamento(Integer codigo) {
        this.codigo = codigo;
    }

    public Medicamento(Integer codigo, String nombreGenerico, String formaFarmaceutica, String presentacion, String laboratorioRegistro, int precio, String tipoMedicamento) {
        this.codigo = codigo;
        this.nombreGenerico = nombreGenerico;
        this.formaFarmaceutica = formaFarmaceutica;
        this.presentacion = presentacion;
        this.laboratorioRegistro = laboratorioRegistro;
        this.precio = precio;
        this.tipoMedicamento = tipoMedicamento;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getNombreGenerico() {
        return nombreGenerico;
    }

    public void setNombreGenerico(String nombreGenerico) {
        this.nombreGenerico = nombreGenerico;
    }

    public String getFormaFarmaceutica() {
        return formaFarmaceutica;
    }

    public void setFormaFarmaceutica(String formaFarmaceutica) {
        this.formaFarmaceutica = formaFarmaceutica;
    }

    public String getPresentacion() {
        return presentacion;
    }

    public void setPresentacion(String presentacion) {
        this.presentacion = presentacion;
    }

    public String getLaboratorioRegistro() {
        return laboratorioRegistro;
    }

    public void setLaboratorioRegistro(String laboratorioRegistro) {
        this.laboratorioRegistro = laboratorioRegistro;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public String getTipoMedicamento() {
        return tipoMedicamento;
    }

    public void setTipoMedicamento(String tipoMedicamento) {
        this.tipoMedicamento = tipoMedicamento;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codigo != null ? codigo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Medicamento)) {
            return false;
        }
        Medicamento other = (Medicamento) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidades_DB.Medicamento[ codigo=" + codigo + " ]";
    }
    
}
