package cn.wbnull.hellotlj.model.game;

import lombok.Data;

/**
 * <p>
 *
 * </p>
 *
 * @author dukunbiao(null)
 * @since 2020-02-05
 */
@Data
public class Gametable {

    private String tableId;
    private String userId;
    private String status;
    private String owner;

    public boolean isOwner() {
        return "1".equals(this.owner);
    }
}
