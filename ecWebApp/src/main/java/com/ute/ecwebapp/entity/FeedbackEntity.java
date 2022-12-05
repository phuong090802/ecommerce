package com.ute.ecwebapp.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Check;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
	private Integer feadBackId;

	@Column(columnDefinition = "NVARCHAR(40)", nullable = false)
	private String review;

	@Column(nullable = false)
	private Integer rating;

	@ManyToOne
	@JoinColumn(name = "seller_id", nullable = false)
	private UserEntity user;

	@ManyToOne
	@JoinColumn(name = "buy_id", nullable = false)
	private UserEntity _user;

}
