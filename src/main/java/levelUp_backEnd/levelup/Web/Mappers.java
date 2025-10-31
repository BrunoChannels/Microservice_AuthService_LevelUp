
package levelUp_backEnd.levelup.Web;

import levelUp_backEnd.levelup.Model.User;
import static levelUp_backEnd.levelup.Dto.Dtos.UserResponse;

public class Mappers {
    public static UserResponse toUserResponse(User u) {
        UserResponse r = new UserResponse();
        r.id = u.getId();
        r.name = u.getName();
        r.email = u.getEmail();
        r.address = u.getAddress();
        r.phone = u.getPhone();
        r.preferences = u.getPreferences();
        return r;
    }
}
