package com.ute.ecwebapp.entity;

import javax.persistence.*;


import org.hibernate.annotations.Check;

import lombok.*;

@Entity
@Table(name = "feedback")
@Check(constraints = "rating BETWEEN 1 AND 5")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FeedbackEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "feedback_id")
	private Integer feedBackId;
	
	private String review;

	@Column(nullable = false)
	private Integer rating;

	@ManyToOne
	@JoinColumn(name = "seller_id", nullable = false)
	private UserEntity seller;

	@ManyToOne
	@JoinColumn(name = "buy_id", nullable = false)
	private UserEntity buyer;

	public FeedbackEntity(String review, Integer rating, UserEntity seller, UserEntity buyer) {
		super();
		this.review = review;
		this.rating = rating;
		this.seller = seller;
		this.buyer = buyer;
	}
	
	
}
