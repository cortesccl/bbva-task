package es.bbva.task.module.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Version;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="markets")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@ToString
@EqualsAndHashCode
public class Market implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Version
    private short version;

    @Column(unique=true)
    private String name;

    @OneToMany(mappedBy = "market")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @Builder.Default
    @JsonIgnore
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<Vwap> vwaps = new HashSet<>();

    public void addVwap(Vwap buildingStaff) {
        if (this.vwaps == null) {
            this.vwaps = new HashSet<>();
        }
        this.vwaps.add(buildingStaff);
        buildingStaff.setMarket(this);
    }

    public void removeVwap(Vwap vwap) {
        if (this.vwaps == null) {
            this.vwaps = new HashSet<>();
        }
        this.vwaps.remove(vwap);
        vwap.setMarket(null);
    }

    public void clearVwap() {
        this.vwaps = new HashSet<>();
    }
}
