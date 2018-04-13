 <g:form action="storePlatformFeedback">
  <div class="form-group">
    <label for="description">Give Feedback::</label>
    <g:textArea name="description" class="form-control"  rows="2" cols="40"/>
    <g:hiddenField name="type" value="${type}" />
  </div>


  <button type="submit" class="btn btn-sm btn-primary">Submit Feedback</button>
</g:form>