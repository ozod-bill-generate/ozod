package com.org.ozod.service;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import com.org.ozod.dto.UserSaleOrderDto;
import com.org.ozod.entity.SaleOrder;
import com.org.ozod.entity.User;
import com.org.ozod.repository.IUserrepository;
import com.org.ozod.util.Constant;
import com.org.ozod.util.PDFGenerator;

@Service
public class ReportServiceImpl implements IReportService{
	
	@Autowired
	private PDFGenerator pDFGenerator;
	
	@Autowired
	private IUserrepository useRepo;

	@Override
	public Resource generateBill(UserSaleOrderDto userProduct) {
		ByteArrayResource resource = null;
		Optional<User> user=useRepo.findById(userProduct.getUserId());
		if(user.isPresent()) {
			pDFGenerator.createPDF(user.get(),userProduct.getSaleOrder(),Constant.REPORT_PDF_PATH);
			List<SaleOrder> listSaleOrder=user.get().getSaleOrder();
			userProduct.getSaleOrder().forEach(sale->{
				SaleOrder saleOrder=new SaleOrder();
				saleOrder.setPrice(Long.parseLong((sale.getPrice().substring(4))));
				saleOrder.setProduct_name(sale.getProduct_name());
				saleOrder.setProductNameId(sale.getProductNameId());
				saleOrder.setProductType(sale.getProductType());
				saleOrder.setQuantity(sale.getQuantity());
				saleOrder.setUser(user.get());
				saleOrder.setRecStat(true);
				listSaleOrder.add(saleOrder);
			});
			useRepo.save(user.get());
			try {
				resource = new ByteArrayResource(toByteArray());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return resource;
	}
	
	 public static byte[] toByteArray() throws Exception {
	      File file = new File(Constant.REPORT_PDF_PATH);
	      FileInputStream fis = new FileInputStream(file);
	      byte [] data = new byte[(int)file.length()];
	      fis.read(data);
	      ByteArrayOutputStream bos = new ByteArrayOutputStream();
	      bos.writeBytes(data);
	      return bos.toByteArray();
	   }

}
