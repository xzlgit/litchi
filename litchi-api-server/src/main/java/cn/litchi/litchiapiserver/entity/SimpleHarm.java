package cn.litchi.litchiapiserver.entity;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class SimpleHarm implements Serializable{
	private Long id;
	private String name;
	private String pic;
}
