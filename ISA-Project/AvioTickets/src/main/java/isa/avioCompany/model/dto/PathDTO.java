package isa.avioCompany.model.dto;

import lombok.Data;

@Data
public class PathDTO {

	private Long id;

	private String start;

	private String end;

	private Integer numberOfTransfer;

	private Long avio_id;
}
