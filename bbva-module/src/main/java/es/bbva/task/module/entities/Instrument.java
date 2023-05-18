package es.bbva.task.module.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Version;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="instruments")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@ToString
//@EqualsAndHashCode(exclude ={"vwaps"}, callSuper = false)
@EqualsAndHashCode(callSuper = false)
public class Instrument implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Version
    private short version;

    @Column(unique=true)
    private String name;

    @OneToMany(mappedBy = "instrument", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @Builder.Default
    @OrderBy("id desc")
    private Set<Vwap> vwaps = new HashSet<>();


    public void addVwap(Vwap buildingStaff) {
        if (this.vwaps == null) {
            this.vwaps = new HashSet<>();
        }
        this.vwaps.add(buildingStaff);
        buildingStaff.setInstrument(this);
    }

    public void removeVwap(Vwap vwap) {
        if (this.vwaps == null) {
            this.vwaps = new HashSet<>();
        }
        this.vwaps.remove(vwap);
        vwap.setInstrument(null);
    }

    public void clearVwap() {
        this.vwaps = new HashSet<>();
    }

}
