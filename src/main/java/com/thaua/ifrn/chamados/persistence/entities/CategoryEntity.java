package com.thaua.ifrn.chamados.persistence.entities;

import com.thaua.ifrn.chamados.domain.categories.UpdateCategory;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "categories_tb")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class CategoryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "category_id")
    private UUID categoryId;

    private String name;

    private String description;

    @ManyToOne
    @JoinColumn(name = "sector_id")
    private SectorEntity sector;

    public void update(UpdateCategory updateCategory) {
        this.name = updateCategory.getName();
        this.description = updateCategory.getDescription();
    }
}
