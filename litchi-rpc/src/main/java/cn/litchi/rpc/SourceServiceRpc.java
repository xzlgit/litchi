package cn.litchi.rpc;

import cn.litchi.model.model.DBLzLitchiType;
import cn.litchi.model.model.DBLzOrchardPicture;
import cn.litchi.model.model.DBLzText;
import cn.litchi.model.model.DBTbContent;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@FeignClient(value = "litchi-source-server")
@RequestMapping("/source")
public interface SourceServiceRpc {

	@GetMapping("/carousepic")
	List<DBTbContent> getCarouselPic();

	@PostMapping(value = "/upload",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	Map<String,String> uploadFile(@RequestPart("file") MultipartFile uploadFile);

	@GetMapping("/orcpic")
	List<DBLzOrchardPicture> getOrcPic(@RequestParam("orcId") Long orcId);

	@GetMapping("/litchitype")
    DBLzLitchiType getLitchiType(@RequestParam("typeId") Long typeId);

	@GetMapping("/litchitext")
	List<DBLzText> getLitchiTextByTypeId(@RequestParam("typeId") Long typeId);
}
