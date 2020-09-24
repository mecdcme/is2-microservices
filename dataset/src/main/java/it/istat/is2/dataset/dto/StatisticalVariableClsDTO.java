package it.istat.is2.dataset.dto;

import it.istat.is2.dataset.domain.StatisticalVariableCls;
import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
public class StatisticalVariableClsDTO {
    private Integer id;
    private String name;
    private String descr;
    private Short order;
    private String nameEng;
    private String nameIta;

    private StatisticalVariableClsDTO(Integer id, String name, String descr, Short order, String nameEng, String nameIta) {
        this.id = id;
        this.name = name;
        this.descr = descr;
        this.order = order;
        this.nameEng = nameEng;
        this.nameIta = nameIta;
    }

    public static class Builder {
        private Integer id;
        private String name;
        private String descr;
        private Short order;
        private String nameEng;
        private String nameIta;

        public Builder fromEntity(StatisticalVariableCls entity) {
            this.id = entity.getId();
            this.name = entity.getName();
            this.descr = entity.getDescr();
            this.order = entity.getOrder();
            this.nameEng = entity.getNameEng();
            this.nameIta = entity.getNameIta();

            return this;
        }

        public StatisticalVariableClsDTO build() {
            return new StatisticalVariableClsDTO(id, name, descr, order, nameEng, nameIta);
        }
    }
}
