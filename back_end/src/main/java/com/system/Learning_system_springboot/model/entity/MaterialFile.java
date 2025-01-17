package com.system.Learning_system_springboot.model.entity;
import jakarta.persistence.*;
@Entity
@Table(name = "material_file")
public class MaterialFile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "file_url", nullable = false, length = 100)
    private String fileUrl;

    @ManyToOne
    @JoinColumn(name = "material_id", nullable = false)
    private Material material;

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


}