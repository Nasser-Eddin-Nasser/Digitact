import { Injectable, SecurityContext } from '@angular/core';
import { DomSanitizer } from '@angular/platform-browser';

/**
 * This service escapes the given content. It also uses a Sanitizer just to be super safe.
 * This service should only be used in an Ion Wrapper.
 */
@Injectable({
  providedIn: 'root',
})
export class ContentSecurityService {
  constructor(private domSanitizer: DomSanitizer) {}

  /**
   * Escape the given string. Also run a Sanitizer to be super safe.
   *
   * @param str The string to escape.
   */
  escapeSecurely(str: string): string {
    let result = this.escapeHtml(str);

    // Even though the content should be properly escaped, let's use the Angular sanitizer to be super safe.
    result = this.domSanitizer.sanitize(SecurityContext.HTML, result);

    return result;
  }

  /**
   * A really simple method that replaces the following characters in a string by their HTML representation:
   * - &
   * - <
   * - \>
   * - "
   * - '
   * - /
   * - `
   * - =
   *
   * @param str The string we want to escape.
   */
  private escapeHtml(str: string): string {
    let result = String(str);

    // The list is based on the one used in mustache.js, see https://github.com/janl/mustache.js/blob/master/mustache.js
    result = result.replace(/[&<>"'\/]/g, (match) => {
      switch (match) {
        case '&':
          return '&amp;';
        case '<':
          return '&lt;';
        case '>':
          return '&gt;';
        case '"':
          return '&quot;';
        // tslint:disable-next-line
        case "'":
          return '&#x27;';
        case '/':
          return '&#x2F;';
        case '`':
          return '&#x60;';
        case '=':
          return '&#x3D;';
        default:
          console.error('Unable to escape a character!');
          return '';
      }
    });

    return result;
  }
}
