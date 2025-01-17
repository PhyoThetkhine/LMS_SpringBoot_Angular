package com.system.Learning_system_springboot.model.dto;

import com.system.Learning_system_springboot.model.entity.Material;

public class MaterialFileDTO {
    private Integer id;
    private String fileUrl;
    private Material material;
    private Integer materialID;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }

    public Material getMaterial() {
        return material;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }

    public Integer getMaterialID() {
        return materialID;
    }

    public void setMaterialID(Integer materialID) {
        this.materialID = materialID;
    }
}
