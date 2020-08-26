package cn.litchi.model.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DBTbCart implements Serializable {
    private Long userId;
    private List<Long> cartItemIds = new ArrayList<>();
}
