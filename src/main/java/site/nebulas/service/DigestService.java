package site.nebulas.service;

import org.springframework.stereotype.Service;
import site.nebulas.beans.Digest;
import site.nebulas.dao.DigestDao;
import javax.annotation.Resource;
import java.util.List;


@Service
public class DigestService {
	@Resource
	private DigestDao digestDao;

    public List<Digest> queryByParam(Digest digest){
        return digestDao.queryByParam(digest);
    }
    public void insert(Digest digest){
        digestDao.insert(digest);
    }
    public void update(Digest digest){
        digestDao.update(digest);
    }

}


