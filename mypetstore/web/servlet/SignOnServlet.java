package csu.web.mypetstore.web.servlet;

import com.mysql.cj.Session;
import csu.web.mypetstore.domain.Account;
import csu.web.mypetstore.domain.Product;
import csu.web.mypetstore.service.AccountService;
import csu.web.mypetstore.service.CatalogService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class SignOnServlet extends HttpServlet {

    private static final String MAIN = "/WEB-INF/jsp/catalog/main.jsp";
    private static final String SIGN_ON_FORM = "/WEB-INF/jsp/account/signon.jsp";

    private String username;
    private String password;

    private Account account;
    private AccountService accountService;

//    private String msg;

    // 从表单提交, 因此用doPost方法
    @Override
//    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        this.username = req.getParameter("username");
//        this.password = req.getParameter("password");
//
//
//        // 校验用户输入的正确性
//        if (!validate()) {
//            req.setAttribute("signOnMsg", this.msg);
//            req.getRequestDispatcher(SIGN_ON_FORM).forward(req, resp);
//        } else {
//            AccountService accountService = new AccountService();
//            Account account = accountService.getAccount(username, password);
//            if (account == null) {
//                this.msg = "用户名或密码错误";
//                req.getRequestDispatcher(SIGN_ON_FORM).forward(req, resp);
//            } else {
//                account.setPassword(null);
//                HttpSession session = req.getSession();
//                session.setAttribute("account", account);
//
//                if (account.isListOption()) {
//                    CatalogService catalogService = new CatalogService();
//                    List<Product> myList = catalogService.getProductListByCategory(account.getFavouriteCategoryId());
//                    session.setAttribute("myList", myList);
//                }
//
////                resp.sendRedirect("mainForm");
//            }
//        }
//
//    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        this.username = req.getParameter("username");
        this.password = req.getParameter("password");

        accountService = new AccountService();
        account = accountService.getAccount(username, password);

        HttpSession session = req.getSession();
        session.setAttribute("account", account);


        //获得输入的验证码值
        String value1 = req.getParameter("vCode");
        /*获取图片的值*/
        String value2 = (String) session.getAttribute("checkcode");
        Boolean isSame = false;
        /*对比两个值（字母不区分大小写）*/
        if (value2.equalsIgnoreCase(value1)) {
            isSame = true;
        }

        if (account == null || !isSame) {
            if (username == null || username.equals("")) {
                session.setAttribute("signOnMsg", "Invalid username or password.  Signon failed.");
            }  else if (!isSame) {
                session.setAttribute("signOnMsg", "Invalid Verification Code.   Signon failed.");
            }

            session.setAttribute("account", null);
            req.getRequestDispatcher(SIGN_ON_FORM).forward(req, resp);
        } else {
            account.setPassword(null);
            req.getRequestDispatcher(MAIN).forward(req, resp);
        }

        if (account.isListOption()) {
            CatalogService catalogService = new CatalogService();
            List<Product> myList = catalogService.getProductListByCategory(account.getFavouriteCategoryId());
            session.setAttribute("myList", myList);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }

    // 还需完善长短校验
    /*private boolean validate() {
        if (this.username == null || this.username.equals("")) {
            this.msg = "用户名不能为空";
            return false;
        }
        if (this.password == null || this.password.equals("")) {
            this.msg = "密码不能为空";
            return false;
        }
        return true;
    }*/
}
