package unii.counter.sevenwonders.validation;

import android.graphics.drawable.Drawable;
import android.widget.EditText;

/**
 * 
 * @author Arkadiusz Pachucy <br>
 *         Validation class for validating fields and displaying a warning<br>
 */
public class ValidationHelper {

	private ValidationHelper() {
	}

	/**
	 * Check if editText is empty <br>
	 * in case of:<br>
	 * -null reference <br>
	 * -no text in editText<br>
	 * return true can set warning in editText <br>
	 * if reference to it is not null <br>
	 * and string was provided <br>
	 * 
	 * @param validatedField
	 *            field to be validated
	 * @param errorMessage
	 *            additional string to display a warning
	 * @return true if reference is null or when there are no text <br>
	 *         in other case return false<br>
	 */
	public static boolean isEditTextEmpty(EditText validatedField,
			String errorMessage) {
		return isEditTextEmpty(validatedField, errorMessage, null);
	}

	/**
	 * Check if editTexts are empty <br>
	 * in case of:<br>
	 * -null reference <br>
	 * -no text in editText<br>
	 * return true and set warning in editText <br>
	 * if reference to it is not null <br>
	 * and string was provided <br>
	 * 
	 * @param validatedFields
	 *            fields to be validated
	 * @param errorMessage
	 *            additional string to display a warning
	 * @return true if at lest one reference is null or when there are no text <br>
	 *         in other case return false<br>
	 */
	public static boolean areEditTextEmptys(String errorMessage,
			EditText... validatedFields) {
		return areEditTextEmptys(errorMessage, null, validatedFields);
	}

	public static boolean areEditTextEmptys(String errorMessage,
			Drawable errorIcon, EditText... validatedFields) {
		boolean atLeastOneIsEmpty = false;
		for (int i = 0; i < validatedFields.length; i++) {
			atLeastOneIsEmpty = atLeastOneIsEmpty
					| isEditTextEmpty(validatedFields[i], errorMessage,
							errorIcon);
		}

		return atLeastOneIsEmpty;
	}

	/**
	 * Check if editText is empty <br>
	 * in case of:<br>
	 * -null reference <br>
	 * -no text in editText<br>
	 * return true can set warning in editText <br>
	 * if reference to it is not null <br>
	 * and string was provided <br>
	 * 
	 * @param validatedField
	 *            field to be validated
	 * @param errorMessage
	 *            additional string to display a warning
	 * @param errorIcon
	 *            icon to be displayed in editText (only if warning is provided)
	 *            in case of null use default icon
	 * @return true if reference is null or when there are no text <br>
	 *         in other case return false<br>
	 */
	public static boolean isEditTextEmpty(EditText validatedField,
			String errorMessage, Drawable errorIcon) {
		// reference is null
		if (validatedField == null) {
			return true;
		}
		// this is emptyfield
		if (isTextEmpty(validatedField.getText().toString())) {
			// display warning in editText
			if (!isTextEmpty(errorMessage)) {
				if (errorIcon == null) {
					validatedField.setError(errorMessage);
				} else {
					validatedField.setError(errorMessage, errorIcon);
				}
			}
			return true;
		}
		return false;
	}

	public static boolean isTextEmpty(String text) {
		return text == null || text.trim().equals("")
				|| text.trim().equals(" ");
	}

	/**
	 * Check if string is an email if not clear editText and display warning
	 * 
	 * @param validatedField
	 *            can be null
	 * @param email
	 * @param errorMessage
	 *            can be null
	 * @return
	 */
	public static boolean isEmailValid(EditText validatedField, String email,
			String errorMessage) {
		if (android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
			return true;
		} else {
			if (validatedField != null) {
				validatedField.setText("");
				if (!isTextEmpty(errorMessage)) {
					validatedField.setError(errorMessage);
				}
			}
			return false;

		}
	}

	/**
	 * Display warning and clear editText if text in editText is not email
	 * 
	 * @param validatedField
	 * @param errorMessage
	 *            can be null
	 * @return
	 */
	public static boolean isEmailValid(EditText validatedField,
			String errorMessage) {
		return isEmailValid(validatedField,
				validatedField.getText().toString(), errorMessage);
	}

	/**
	 * Check if string is an email
	 * 
	 * @param email
	 * @return
	 */
	public static boolean isEmailValid(String email) {
		return isEmailValid(null, email, null);
	}
}
