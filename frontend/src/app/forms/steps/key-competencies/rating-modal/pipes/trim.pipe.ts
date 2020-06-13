import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'trim',
})
export class TrimPipe implements PipeTransform {
  /**
   * This **pure** Pipe allows us to trim whitespaces from the beginning and end of a string.
   * So, it is basically just a wrapper around the `trim` function
   * (but more performant compared to directly calling `trim` in the template, since it is a pure pipe).
   */
  transform(text: string): string {
    return text.trim();
  }
}
