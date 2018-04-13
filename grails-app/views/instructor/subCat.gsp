
<g:if test="${check == 0}">
     Sud-Category(if any):
      <g:select class="form-control" optionKey="id" from="${scat}" optionValue="${{it.name}}" name="sscat" onchange="addCats(this.value)" noSelection="${['':'Please Select One...']}" />
      Category ${msg}
</g:if>
<g:else>
     Sud-Category:<g:select class="form-control" optionKey="id" from="${scat}" optionValue="${{it.name}}" name="sscat" onchange="addCat(this.value)" /> ${msg}
</g:else>