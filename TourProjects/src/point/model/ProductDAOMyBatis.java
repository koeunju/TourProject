package point.model;

import java.util.HashMap;

import java.util.List;
import java.util.Map;

import common.model.DAOMyBatisBase;

public class ProductDAOMyBatis extends DAOMyBatisBase {

    private final String NS = "common.mapper.ProductMapper";

    /*카테고리 목록*/
    public List<Product_CategoryVO> getCategory() {
        try {
            ses = this.getSessionFactory().openSession(true);
            List<Product_CategoryVO> clist = ses.selectList(NS + ".getCategory");
            return clist;
        } finally {
            close();
        }
    }

    public List<ProductVO> selectByCategory(String cgnum) {

        try {
            ses = this.getSessionFactory().openSession(true);
            List<ProductVO> plist = ses.selectList(NS + ".selectByCategory", cgnum);
            return plist;
        } finally {
            close();
        }
    }

    public ProductVO selectByPnum(String pnum) {
        try {
            ses = this.getSessionFactory().openSession(true);
            ProductVO pvo = ses.selectOne(NS + ".selectByPnum", pnum);
            return pvo;
        } finally {
            close();
        }
    }

    public int getFindTotalCount(String findKeyword) {
        try {
            ses = this.getSessionFactory().openSession(true);
            int n = ses.selectOne(NS + ".getFindTotalCount", findKeyword);
            return n;
        } finally {
            close();
        }
    }

    public List<ProductVO> getFindList(int start, int end, String findKeyword) {
        try {
            ses = this.getSessionFactory().openSession(true);
            Map<String, Object> map = new HashMap<>();
            map.put("findKeyword", findKeyword);
            map.put("start", start);
            map.put("end", end);
            return ses.selectList(NS + ".getFindList", map);
        } finally {
            close();
        }

    }

    public int getProductTotalCount() {
        try {
            ses = this.getSessionFactory().openSession(true);
            int cnt = ses.selectOne(NS + ".getProductTotalCount");
            return cnt;
        } finally {
            close();
        }
    }

    public List<ProductVO> getProdList(int start, int end) {
        try {
            Map<String, Integer> map = new HashMap<>();
            map.put("start", start);
            map.put("end", end);

            ses = this.getSessionFactory().openSession(true);
            List<ProductVO> arr = ses.selectList(NS + ".getProdList", map);
            return arr;
        } finally {
            close();
        }
    }

    /** 여기부터 admin입니다*/
    /** 상품등록하는 메소드*/
    public int insertProduct(ProductVO product) {
        try {
            ses = this.getSessionFactory().openSession(true);
            int n = ses.insert(NS + ".insertProduct", product);
            return n;
        } finally {
            close();
        }
    }

}



