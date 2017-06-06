package edu.hm.huberneumeier.shareit.media.media;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

/**
 * The Medium definition.
 *
 * @author Tobias Huber
 * @author Andreas Neumeier
 * @version 2017-04-12
 */
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Medium {
    //TODO how should this be done book extends medium and disc extends medium, so both need to get the title attribute from parent class
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Integer id;

    private String title;

    /**
     * Default constructor.
     */
    public Medium() {
    }

    /**
     * Constructor.
     *
     * @param title the title of the medium.
     */
    public Medium(String title) {
        this.title = title;
    }

    /**
     * Getter of the title.
     *
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    @Override
    public int hashCode() {
        return title != null ? title.hashCode() : 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Medium medium = (Medium) o;

        return title != null ? title.equals(medium.title) : medium.title == null;
    }
}
