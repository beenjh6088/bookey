class ItemI1F4B1 extends HTMLElement {
	connectedCallback() {
		let src = this.getAttribute("src");
		let bookNM = this.getAttribute("bookNM");
		let rentalDate = this.getAttribute("rentalDate");
		let dueDate = this.getAttribute("dueDate");
		let rentalValue = this.getAttribute("rentalValue");
		let buttonValue = this.getAttribute("buttonValue");
		let bookID = this.getAttribute("bookID");
		let userID = this.getAttribute("userID");
		let recNum = this.getAttribute("rectNum");
		let rentalID = this.getAttribute("rentalID");
		let rentalCode = this.getAttribute("rentalCode");
		let queue = this.getAttribute("queue");
		
		let elImage = "";
		let elBookNM = "";
		let elRentalDate = "";
		let elDueDate = "";
		let elRentalValue = "";
		let elButton = "";
		let elQueue = "";
		
		if(src != null) elImage = `<img src="${src}" >`;
		if(bookNM != null) elBookNM = `<li class="bookNM"><label for="">Book Name</label><span>${bookNM}</span></li>`;
		if(rentalDate != null) elRentalDate = `<li class="rentalDate"><label for="">Rental Date</label><span>${rentalDate}</span></li>`;
		if(dueDate != null) elDueDate = `<li class="dueDate"><label for="">Due Date</label><span>${dueDate}</span></li>`;
		if(rentalValue != null) elRentalValue = `<li class="rentalValue"><label for="">Rental</label><span>${rentalValue}</span></li>`;
		if(buttonValue != null) elButton = `<input type="button" value="${buttonValue}" class="button" data-bookID="${bookID}" data-userID="${userID}"/>`;
		if(queue != null) elQueue = `<li class="queue"><label for="">Queue</label><span>${queue}</span></li>`;
		if(status != null) elQueue = `<li class="queue"><label for="">Queue</label><span>${queue}</span></li>`;
		
				
		this.classList.add("bookItem")
		this.innerHTML = `
			  <div class="imageArea">
			    ${elImage}
			  </div>
			  <div class="detailArea">
			    <ul class="detailList">
			      ${elBookNM}
			      ${elRentalDate}
			      ${elDueDate}
			      ${elRentalValue}
			      ${elQueue}
			    </ul>
			    <div class="buttons">
			    	${elButton}
			    </div>
			  </div>
		`
	}
}

// Registration for customed tag
customElements.define('bky-item-i1f4b1', ItemI1F4B1);