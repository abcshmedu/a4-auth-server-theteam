package hm.edu.huberneumeier.shareit.auth;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import edu.hm.huberneumeier.shareit.auth.logic.authorisation.ValidationResult;
import edu.hm.huberneumeier.shareit.auth.logic.authorisation.ValidationState;
import edu.hm.huberneumeier.shareit.auth.media.jsonMappings.AuthorisationIDRequest;
import edu.hm.huberneumeier.shareit.auth.media.jsonMappings.UnauthenticatedUser;
import edu.hm.huberneumeier.shareit.auth.resources.AuthenticationResource;
import edu.hm.huberneumeier.shareit.auth.resources.AuthorisationResource;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.ws.rs.core.Response;
import java.io.IOException;

/**
 * Tests for the authorisation service of share it.
 *
 * @author Tobias Huber
 * @author Andreas Neumeier
 * @version 28.05.2017
 */
public class AuthorisationTests {

    private static final AuthorisationResource authorisationResource = new AuthorisationResource();
    private String validToken;

    private static String jsonMapper(Object object) {
        ObjectMapper mapper = new ObjectMapper();
        String jsonString = null;
        try {
            jsonString = mapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return jsonString;
    }

    @Before
    public void getToken() throws IOException {
        UnauthenticatedUser correctUser = new UnauthenticatedUser("admin", "123456");
        String json = (String) new AuthenticationResource().authenticateUser(correctUser).getEntity();
        ObjectNode object = new ObjectMapper().readValue(json, ObjectNode.class);
        JsonNode node = object.get("key");
        validToken = node.textValue();
    }

    @Test
    public void unauthorized() throws IOException {
        Response response = authorisationResource.validateRequest("invalideToken", new AuthorisationIDRequest(0));
        ObjectMapper objectMapper = new ObjectMapper();
        ValidationResult validationResult = objectMapper.readValue(response.getEntity().toString(), ValidationResult.class);

        Assert.assertEquals(ValidationState.TOKEN_INVALID, validationResult.getValidationState());
    }

    @Test
    public void noPermission() throws IOException {
        Response response = authorisationResource.validateRequest(validToken, new AuthorisationIDRequest(0));
        ObjectMapper objectMapper = new ObjectMapper();
        ValidationResult validationResult = objectMapper.readValue(response.getEntity().toString(), ValidationResult.class);

        Assert.assertEquals(ValidationState.NO_PERMISSON, validationResult.getValidationState());
    }

    @Test
    public void permissionGranted() throws IOException {
        Response response = authorisationResource.validateRequest(validToken, new AuthorisationIDRequest(1));
        ObjectMapper objectMapper = new ObjectMapper();
        ValidationResult validationResult = objectMapper.readValue(response.getEntity().toString(), ValidationResult.class);

        Assert.assertEquals(ValidationState.SUCCESS, validationResult.getValidationState());
    }
}
