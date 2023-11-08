package csu.web.mypetstore.web.servlet;

import csu.web.mypetstore.domain.Account;
import csu.web.mypetstore.service.AccountService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class NewAccountServlet extends HttpServlet {
    private static final String MAIN = "/WEB-INF/jsp/catalog/main.jsp";
    private static final String NEW_ACCOUNT_FORM = "/WEB-INF/jsp/account/newAccountForm.jsp";


    private Account account;
    private Account account1;
    private AccountService accountService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        HttpSession session = req.getSession();
        account = null;
        session.setAttribute("account", account);

        //获得输入的验证码值
        String value1=req.getParameter("vCode");
        /*获取图片的值*/
        String value2=(String)session.getAttribute("checkcode");
        Boolean isSame = false;
        /*对比两个值（字母不区分大小写）*/
        if(value2.equalsIgnoreCase(value1)){
            isSame = true;
        }

        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String firstName = req.getParameter("firstName");
        String lastName = req.getParameter("lastName");
        String email = req.getParameter("email");
        String phone = req.getParameter("phone");
        String address1 = req.getParameter("address1");
        String address2 = req.getParameter("address2");
        String city = req.getParameter("city");
        String state = req.getParameter("state");
        String zip = req.getParameter("zip");
        String country = req.getParameter("country");
        String languagePreference = req.getParameter("languagePreference");
        String favouriteCategoryId = req.getParameter("favouriteCategoryId");
        String listOption = req.getParameter("listOption");
        String bannerOption = req.getParameter("bannerOption");

        account1 = new Account();
        account1.setUsername(username);
        account1.setPassword(password);
        account1.setFirstName(firstName);
        account1.setLastName(lastName);
        account1.setEmail(email);
        account1.setPhone(phone);
        account1.setAddress1(address1);
        account1.setAddress2(address2);
        account1.setCity(city);
        account1.setState(state);
        account1.setZip(zip);
        account1.setCountry(country);
        account1.setLanguagePreference(languagePreference);
        account1.setFavouriteCategoryId(favouriteCategoryId);
        account1.setListOption(Boolean.parseBoolean(listOption));
        account1.setBannerOption(Boolean.parseBoolean(bannerOption));

        if (isSame) {
            accountService = new AccountService();
            accountService.insertAccount(account1);

            req.getRequestDispatcher(MAIN).forward(req, resp);
        } else {
            session.setAttribute("messageAccount", "Invalid Verification Code.");

            req.getRequestDispatcher(NEW_ACCOUNT_FORM).forward(req, resp);
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
