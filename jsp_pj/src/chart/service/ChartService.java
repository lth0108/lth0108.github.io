package chart.service;

import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import shop.model.dao.ShopDAO;
import shop.model.dto.ShopDTO;

public class ChartService {
	
	public JSONObject getChartData() {
		ShopDAO dao_shop = new ShopDAO();
		List<ShopDTO> list = dao_shop.getSelectShopProductGroup();
		
		JSONObject data = new JSONObject();
		
		JSONObject col1 = new JSONObject();
		JSONObject col2 = new JSONObject();
		JSONArray title = new JSONArray();
		
		col1.put("label", "상품명");
		col1.put("type", "string");
		col2.put("label", "금액");
		col2.put("type", "number");
		
		title.add(col1);
		title.add(col2);
		data.put("cols", title);
		
		JSONArray body = new JSONArray();
		for(ShopDTO dto : list) {
			JSONObject name = new JSONObject();
			name.put("v", dto.getProductName());
			JSONObject money = new JSONObject();
			money.put("v", dto.getTotPrice());
			JSONArray row = new JSONArray();
			row.add(name);
			row.add(money);
			JSONObject cell = new JSONObject();
			cell.put("c", row);
			body.add(cell);
		}
		data.put("rows", body);
		return data;
	}

}
