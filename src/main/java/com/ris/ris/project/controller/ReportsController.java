package com.ris.ris.project.controller;

import com.ris.ris.project.model.Auction;
import com.ris.ris.project.model.AuctionState;
import com.ris.ris.project.model.User;
import com.ris.ris.project.repository.AuctionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Controller
@RequestMapping("user/auctions/reports")
public class ReportsController {
    @Autowired
    AuctionRepository ar;

    @Autowired
    HttpSession session;

    @RequestMapping("/activeAuctionsReport")
    public String activeAuctionsReport(HttpServletRequest request, HttpServletResponse response)throws Exception{
        User user = (User) session.getAttribute("user");
        List<Auction> auctions = ar.findAllBySellerAndAuctionState(user, AuctionState.ACTIVE);
        List<ModifiedAuction> modAuctions = auctions.stream()
                .map(auction -> new ModifiedAuction(auction.getTitle(),
                        auction.getCategory()+"",
                        auction.getDescription(),
                        (auction.getBidders()!=null)?auction.getBidders().first().getAmount():Float.NaN)).collect(Collectors.toList());

        response.setContentType("text/html");
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(modAuctions);
        InputStream inputStream = this.getClass().getResourceAsStream("../resources/reports/Report.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(inputStream);

        Map<String, Object> params = new HashMap<>();
        params.put("param","param");

        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport,params,dataSource);
        inputStream.close();

        response.setContentType("application/x-download");
        response.addHeader("Content-disposition", "attachment; filename=ActiveAuctionsReport.pdf");
        OutputStream out = response.getOutputStream();
        JasperExportManager.exportReportToPdfStream(jasperPrint, out);
        return "/users/userProfile";
    }

    private class ModifiedAuction{
        private String title;
        private String category;
        private String description;
        private Float maxBid;

        public ModifiedAuction(String title, String category, String description, Double maxBid) {
            this.title = title;
            this.category = category;
            this.description = description;
            this.maxBid = Float.valueOf(maxBid.toString());
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getCategory() {
            return category;
        }

        public void setCategory(String category) {
            this.category = category;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public Float getMaxBid() {
            return maxBid;
        }

        public void setMaxBid(Float maxBid) {
            this.maxBid = maxBid;
        }
    }
}
