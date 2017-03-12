package site.nebulas.dao;

import site.nebulas.beans.Digest;
import java.util.List;

/**
 * @author Honghui
 * @date 20170312
 */
public interface DigestDao {

    public List<Digest> queryByParam(Digest digest);
    public void insert(Digest digest);
    public void update(Digest digest);
}

