
const allvotes = document.querySelectorAll(".upvote, .downvote");
for (let i=0; i<allvotes.length; i++){
    let className = allvotes[i].className;
    allvotes[i].addEventListener('click', function(event) {
       const direction = className === 'upvote' ? 1 : -1;
       const linkID = this.getAttribute("data-id");
       const voteSum = document.getElementById("votecount-" + linkID);
       const voteSumValue = voteSum.innerHTML;
       fetch(`http://localhost:9258/vote/link/${linkID}/direction/${direction}/votecount/${voteSumValue}`)
           .then(response => response.json())
           .then(data => voteSum.innerHTML = data)
           .catch(err => console.error(err))
    });
}
