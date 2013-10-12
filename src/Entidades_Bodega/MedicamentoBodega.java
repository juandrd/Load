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
@Table(name = "medicamento_bodega")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MedicamentoBodega.findAll", query = "SELECT m FROM MedicamentoBodega m"),
    @NamedQuery(name = "MedicamentoBodega.findByMedicamentoKey", query = "SELECT m FROM MedicamentoBodega m WHERE m.medicamentoKey = :medicamentoKey"),
    @NamedQuery(name = "MedicamentoBodega.findByCodigoMedicamento", query = "SELECT m FROM MedicamentoBodega m WHERE m.codigoMedicamento = :codigoMedicamento"),
    @NamedQuery(name = "MedicamentoBodega.findByNombre", query = "SELECT m FROM MedicamentoBodega m WHERE m.nombre = :nombre"),
    @NamedQuery(name = "MedicamentoBodega.findByFormaFarmaceutica", query = "SELECT m FROM MedicamentoBodega m WHERE m.formaFarmaceutica = :formaFarmaceutica"),
    @NamedQuery(name = "MedicamentoBodega.findByPresentacion", query = "SELECT m FROM MedicamentoBodega m WHERE m.presentacion = :presentacion"),
    @NamedQuery(name = "MedicamentoBodega.findByLabRegistro", query = "SELECT m FROM MedicamentoBodega m WHERE m.labRegistro = :labRegistro"),
    @NamedQuery(name = "MedicamentoBodega.findByPrecio", query = "SELECT m FROM MedicamentoBodega m WHERE m.precio = :precio"),
    @NamedQuery(name = "MedicamentoBodega.findByTipo", query = "SELECT m FROM MedicamentoBodega m WHERE m.tipo = :tipo")})
public class MedicamentoBodega implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "medicamento_key")
    private Integer medicamentoKey;
    @Column(name = "codigo_medicamento")
    private String codigoMedicamento;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "forma_farmaceutica")
    private String formaFarmaceutica;
    @Column(name = "presentacion")
    private String presentacion;
    @Column(name = "lab_registro")
    private String labRegistro;
    @Column(name = "precio")
    private Integer precio;
    @Column(name = "tipo")
    private String tipo;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "medicamentoBodega")
    private List<FormulasBodega> formulasBodegaList;

    public MedicamentoBodega() {
    }

    public MedicamentoBodega(Integer medicamentoKey) {
        this.medicamentoKey = medicamentoKey;
    }

    public Integer getMedicamentoKey() {
        return medicamentoKey;
    }

    public void setMedicamentoKey(Integer medicamentoKey) {
        this.medicamentoKey = medicamentoKey;
    }

    public String getCodigoMedicamento() {
        return codigoMedicamento;
    }

    public void setCodigoMedicamento(String codigoMedicamento) {
        this.codigoMedicamento = codigoMedicamento;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
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

    public String getLabRegistro() {
        return labRegistro;
    }

    public void setLabRegistro(String labRegistro) {
        this.labRegistro = labRegistro;
    }

    public Integer getPrecio() {
        return precio;
    }

    public void setPrecio(Integer precio) {
        this.precio = precio;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    @XmlTransient
    public List<FormulasBodega> getFormulasBodegaList() {
        return formulasBodegaList;
    }

    public void setFormulasBodegaList(List<FormulasBodega> formulasBodegaList) {
        this.formulasBodegaList = formulasBodegaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (medicamentoKey != null ? medicamentoKey.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MedicamentoBodega)) {
            return false;
        }
        MedicamentoBodega other = (MedicamentoBodega) object;
        if ((this.medicamentoKey == null && other.medicamentoKey != null) || (this.medicamentoKey != null && !this.medicamentoKey.equals(other.medicamentoKey))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidades_Bodega.MedicamentoBodega[ medicamentoKey=" + medicamentoKey + " ]";
    }
    
}
