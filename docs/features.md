<style>
img.screenshot {
  width: 200px;
  border: 1px solid #e0e0e0;
}
</style>

# Features

Currently, most of the job applications at job fairs are handled "manually" on paper. Digitact is an app that brings this whole process into the digital era.

Digitact is built with simplicity and good UX in mind. Almost all features are self-explanatory.

This is a quick overview of the most important features.

## Landing page

<img class="screenshot" src="images/landing-page.png">

The landing page (also called "home page") is the very first screen the user sees when he opens the app. Here, right underneath the logo, a short headline related to the company is shown.

There is only one prominent button: "Apply now", which directly starts the job application process. 

## The application form

### "Welcome" Step

<img class="screenshot" src="images/form--welcome--empty.png">
<img class="screenshot" src="images/form--welcome--filled.png">

Once the user has pressed the "Apply now" button, he gets redirected to the actual job application form.

In the top bar, the number of the current "Step" is shown. Since we are showing the "Welcome" Step here (which is the very first one), it says "Step 1 / 2". On the right, a hamburger-esque menu icon is shown. Clicking on it will reveal an overview of the Steps (more about that later).

Directly underneath the top bar, there is a progress bar. Once you have filled out all required data of a particular Step, it will instantly move to the right. If the progress bar has reached the right border, it means that all Steps have successfully been filled out and the form may be submitted.

### Side menu

<img class="screenshot" src="images/form--side-menu.png">

As already mentioned before, there is a menu on the right, which displays an overview of all the Steps. Once all required fields of a particular Step have been filled, a checkmark will appear for the respective menu item. This allows to quickly figure out where data is still missing. Clicking on a menu item allows to jump to this Step.

Also, the currently visible Step is highlighted in a different color. 

### "Contact Information" Step

<img class="screenshot" src="images/form--contact-information--empty.png">

In the second Step, the user shall enter contact information.

### Submit

<img class="screenshot" src="images/form--submit--disabled.png">
<img class="screenshot" src="images/form--submit--enabled.png">

Finally, the applicant reaches the "Submit" page. If there are still some fields missing in one of the previous Steps, he cannot submit the form. Instead, he is informed that the Side Menu will show where he still needs to enter some data. (As explained before, the progress bar also shows this kind of information: All necessary data have been filled in once the bar reaches the right border.)

### "Done" page

<img class="screenshot" src="images/form--done.png">

Once the form has been submitted, the applicant is informed that he is done and there is nothing left he needs to do.

The job application is now stored locally on the device. The HR team member may now click on "Quit" in order to go back to the home screen. Then, a new job application can be entered. Or, he may click on "Continue". This will show a screen where a rating of the applicant is possible. It is also possible to perform this rating later.

## Applicant rating page

<img class="screenshot" src="images/applicant-rating--first-screen.png">

The rating of the applicant is done using a similar UI as in the main job application form.
