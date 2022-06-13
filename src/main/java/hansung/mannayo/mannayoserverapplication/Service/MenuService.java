package hansung.mannayo.mannayoserverapplication.Service;

import hansung.mannayo.mannayoserverapplication.Model.Entity.Menu;

import java.util.List;
import java.util.Optional;

public interface MenuService {

    Optional<List<Menu>> findMenuByRestaurantId(Long id);

    Optional<Menu> findById(Long id);

    Long insert(Menu menu);

    void updateImageAddress(Menu menu);

}
