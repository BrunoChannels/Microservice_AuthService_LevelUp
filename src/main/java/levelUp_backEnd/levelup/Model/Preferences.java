
package levelUp_backEnd.levelup.Model;

import jakarta.persistence.Embeddable;

@Embeddable
public class Preferences {
    private boolean newsletter = true;
    private boolean promos = false;

    public boolean isNewsletter() { return newsletter; }
    public void setNewsletter(boolean newsletter) { this.newsletter = newsletter; }

    public boolean isPromos() { return promos; }
    public void setPromos(boolean promos) { this.promos = promos; }
}
