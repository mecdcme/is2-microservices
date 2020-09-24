/**
 * Copyright 2019 ISTAT
 *
 * Licensed under the EUPL, Version 1.1 or – as soon they will be approved by
 * the European Commission - subsequent versions of the EUPL (the "Licence");
 * You may not use this work except in compliance with the Licence. You may
 * obtain a copy of the Licence at:
 *
 * http://ec.europa.eu/idabc/eupl5
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the Licence is distributed on an "AS IS" basis, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * Licence for the specific language governing permissions and limitations under
 * the Licence.
 *
 * @author Francesco Amato <framato @ istat.it>
 * @author Mauro Bruno <mbruno @ istat.it>
 * @author Paolo Francescangeli  <pafrance @ istat.it>
 * @author Renzo Iannacone <iannacone @ istat.it>
 * @author Stefano Macone <macone @ istat.it>
 * @version 1.0
 */
package it.istat.is2.dataset.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "IS2_DATASET_FILE")
public class DatasetFile implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;
    @Column(name = "FILE_NAME")
    private String fileName;
   
    @Column(name = "FILE_LABEL")
    private String fileLabel;
    @Column(name = "FILE_FORMAT")
    private String fileFormat;
    @Column(name = "FIELD_SEPARATOR")
    private String fieldSeparator;
    @Column(name = "TOTAL_ROWS")
    private Integer totalRows;
    @Column(name = "LAST_UPDATE")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date lastUpdate;

    @ManyToOne(optional = true, fetch = FetchType.LAZY)
    @JoinColumn(name = "CLS_DATA_TYPE_ID", nullable = true)
    private DataTypeCls dataType;

    @OneToOne
    @JoinColumn(name = "WORK_SESSION_ID")
    private WorkSession workSession;

    @OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.ALL}, mappedBy = "datasetFile", orphanRemoval = true)
    @JsonManagedReference
    @OrderBy(value = "orderCode ASC")
    private List<DatasetColumn> columns;

    public DatasetFile(Long id) {
        super();
        this.id = id;
    }

    public DatasetFile() {
        super();

    }

    public Short getSizeCol() {
        return (short) columns.size();
    }

    public String getNameColFormId(String id) {
        DatasetColumn dsc;
        String nameCol = "";

        if (id != null) {
            int id2 = Integer.parseInt(id);
            for (int i = 0; i < this.columns.size(); i++) {
                dsc = columns.get(i);
                if (dsc.getId() == id2) {
                    nameCol = dsc.getName();
                }
            }
        }

        return nameCol;
    }
}
