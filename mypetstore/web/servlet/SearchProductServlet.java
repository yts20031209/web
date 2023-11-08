package csu.web.mypetstore.web.servlet;

import csu.web.mypetstore.domain.Account;
import csu.web.mypetstore.domain.Product;
import csu.web.mypetstore.service.CatalogService;
import csu.web.mypetstore.service.LogService;
import csu.web.mypetstore.web.servlet.ProductAutoCompleteServlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class SearchProductServlet extends HttpServlet {
    private static final String SEARCH_PRODUCTS = "/WEB-INF/jsp/catalog/searchProducts.jsp";

    private String keyword;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Product> productList = null;
        try {
//            System.out.println("111");
            keyword = req.getParameter("keyword");
            System.out.println("keyword : " + keyword);
            CatalogService service = new CatalogService();
            productList = service.searchProductList(keyword);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // 保存数据
        HttpSession session = req.getSession();
        session.setAttribute("keyword", keyword);
        session.setAttribute("productList", productList);

//        Account account = (Account)session.getAttribute("account");

        /*if (account != null) {
            HttpServletRequest httpRequest = req;
            String strBackUrl = "http://" + req.getServerName() + ":" + req.getServerPort()
                    + httpRequest.getContextPath() + httpRequest.getServletPath() + "?" + (httpRequest.getQueryString());

            LogService logService = new LogService();

        }*/

        req.getRequestDispatcher(SEARCH_PRODUCTS).forward(req, resp);

    }
}
